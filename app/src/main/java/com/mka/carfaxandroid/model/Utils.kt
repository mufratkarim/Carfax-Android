package com.mka.carfaxandroid.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

fun loadImage(localCar: LocalCarData, imageView: ImageView) {

    Picasso.get()
        .load(localCar.photoUrl)
        .placeholder(android.R.drawable.stat_sys_download)
        .error(android.R.drawable.stat_notify_error)
        .into(imageView)

}

fun callDealer(localCar: LocalCarData, context: Context, button: Button) {
    button.setOnClickListener {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + localCar.dealerNum)
        context.startActivity(dialIntent)
    }
}

fun screenTexts(
    localCar: LocalCarData, carName: TextView,
    carPriceMileage: TextView, carLocation: TextView,
    carExtColor: TextView?, carIntColor: TextView?,
    carDriveType: TextView?, carTransmission: TextView?,
    carBodyStyle: TextView?, carEngine: TextView?, carFuel: TextView?
) {
    val title = StringBuilder()
    val priceMileage = StringBuilder()
    val location = StringBuilder()
    title
        .append(localCar.year).append(" ")
        .append(localCar.make).append(" ")
        .append(localCar.model).append(" ")
        .append(localCar.trim)
    priceMileage
        .append("$ ").append(DecimalFormat("#,###").format(localCar.price))
        .append(" | ")
        .append(String.format("%.1f", ((localCar.mileage!!.toDouble()) / 1000)))
        .append("k mi")
    location
        .append(localCar.city).append(", ")
        .append(localCar.state)
    carName.text = title
    carPriceMileage.text = priceMileage
    carLocation.text = location

    if (carExtColor != null) {
        carExtColor.text = localCar.exteriorColor
    }
    if (carIntColor != null) {
        carIntColor.text = localCar.interiorColor
    }
    if (carDriveType != null) {
        carDriveType.text = localCar.driveType
    }
    if (carTransmission != null) {
        carTransmission.text = localCar.transmission
    }
    if (carBodyStyle != null) {
        carBodyStyle.text = localCar.bodyStyle
    }
    if (carEngine != null) {
        carEngine.text = localCar.engine
    }
    if (carFuel != null) {
        carFuel.text = localCar.fuel
    }
}


