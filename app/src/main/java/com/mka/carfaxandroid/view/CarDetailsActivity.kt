package com.mka.carfaxandroid.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mka.carfaxandroid.R
import com.mka.carfaxandroid.model.callDealer
import com.mka.carfaxandroid.model.loadImage
import com.mka.carfaxandroid.model.screenTexts
import com.mka.carfaxandroid.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.activity_car_details.*

class CarDetailsActivity : AppCompatActivity() {

    lateinit var viewModel: CarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)

        supportActionBar!!.hide()
        val id = intent.getIntExtra("localId", 1)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(CarViewModel::class.java)

        viewModel.getLocalCar(id).observe(this, {
            screenTexts(
                it, details_car_name, details_car_price_mileage, details_car_location,
                details_ext_color, details_int_color, details_drive_type, details_transmission,
                details_body_style, details_engine, details_fuel
            )
            loadImage(it, details_car_image)
            callDealer(it, this, details_button_call_dealer)
        })


    }
}