package com.mka.carfaxandroid.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mka.carfaxandroid.R
import com.mka.carfaxandroid.model.LocalCarData
import com.mka.carfaxandroid.model.callDealer
import com.mka.carfaxandroid.model.loadImage
import com.mka.carfaxandroid.model.screenTexts
import kotlinx.android.synthetic.main.item_car.view.*

class CarAdapter(private var localCars: LiveData<List<LocalCarData>>) :
    RecyclerView.Adapter<CarAdapter.CarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        return CarHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        if (localCars.value!!.isNotEmpty()) {
            holder.bind(localCars.value!![position])
        }

    }

    override fun getItemCount(): Int {
        if (localCars.value != null) {
            return localCars.value!!.size
        }
        return 0
    }

    class CarHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val context = view.context

        fun bind(localCarLists: LocalCarData) {
            screenTexts(localCarLists, itemView.recycler_car_name, itemView.recycler_car_price_mileage, itemView.recycler_car_location,
                null, null, null, null, null, null, null)
            loadImage(localCarLists, itemView.recycler_car_image)
            carImageClickListener(localCarLists)

            callDealer(localCarLists, context, itemView.recycler_button_call_dealer)
        }

        private fun carImageClickListener(localCarLists: LocalCarData) {
            itemView.recycler_car_image.setOnClickListener {
                val detailIntent = Intent(context, CarDetailsActivity::class.java).apply {
                    this.putExtra("localId", localCarLists.id)
                }
                context.startActivity(detailIntent)
            }
        }
    }
}