package com.mka.carfaxandroid.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mka.carfaxandroid.R
import com.mka.carfaxandroid.model.LocalCarData
import com.mka.carfaxandroid.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CarViewModel
    private lateinit var allLocalCarData: LiveData<List<LocalCarData>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CarViewModel::class.java)
        observeViewModel()

        // viewModel.deleteAllLocalCars()


    }

    private fun observeViewModel() {

        viewModel.getAllLocalCars().observe(this, {

            if (it.isEmpty()) {
                viewModel.fetchCarInfo()
            }

            allLocalCarData = viewModel.getAllLocalCars()
            parent_recyclerView.visibility = View.VISIBLE
            progressBar_countryList.visibility = View.GONE

            parent_recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CarAdapter(allLocalCarData)
            }

        })
        viewModel.carError.observe(this, {

            if (allLocalCarData.value == null) {
                parent_error_textView.visibility = View.VISIBLE
                progressBar_countryList.visibility = View.GONE

                if (viewModel.carError.value != true) {
                    parent_error_textView.text = getString(R.string.sys_error_text)
                }
            }

        })
    }
}