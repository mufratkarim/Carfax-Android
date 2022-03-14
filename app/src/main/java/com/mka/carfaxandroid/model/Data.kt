package com.mka.carfaxandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "local_car_data_table")
data class LocalCarData(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "photo_url") var photoUrl: String?,
    @ColumnInfo(name = "year") var year: String? = null,
    @ColumnInfo(name = "make") var make: String? = null,
    @ColumnInfo(name = "model") var model: String? = null,
    @ColumnInfo(name = "trim") var trim: String? = null,
    @ColumnInfo(name = "price") var price: Int?,
    @ColumnInfo(name = "mileage") var mileage: Int?,
    @ColumnInfo(name = "city") var city: String?,
    @ColumnInfo(name = "state") var state: String?,
    @ColumnInfo(name = "interior_color") var interiorColor: String?,
    @ColumnInfo(name = "exterior_color") var exteriorColor: String?,
    @ColumnInfo(name = "drive_type") var driveType: String?,
    @ColumnInfo(name = "transmission") var transmission: String?,
    @ColumnInfo(name = "engine") var engine: String?,
    @ColumnInfo(name = "body_style") var bodyStyle: String?,
    @ColumnInfo(name = "dealer_number") var dealerNum: String?,
    @ColumnInfo(name = "fuel") var fuel: String?
)

data class Car(
    @SerializedName("listings") var listings: List<Listings> = listOf()
)

data class Dealer(
    @SerializedName("address") var address: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("state") var state: String? = null,
)

data class FirstPhoto(
    @SerializedName("large") var large: String? = null,
)

data class Images(
    @SerializedName("firstPhoto") var firstPhoto: FirstPhoto? = FirstPhoto()
)

data class Listings(
    @SerializedName("bodytype") var bodytype: String? = null,
    @SerializedName("currentPrice") var currentPrice: Int? = null,
    @SerializedName("dealer") var dealer: Dealer? = Dealer(),
    @SerializedName("drivetype") var drivetype: String? = null,
    @SerializedName("images") var images: Images? = Images(),
    @SerializedName("engine") var engine: String? = null,
    @SerializedName("exteriorColor") var exteriorColor: String? = null,
    @SerializedName("interiorColor") var interiorColor: String? = null,
    @SerializedName("mileage") var mileage: Int? = null,
    @SerializedName("transmission") var transmission: String? = null,
    @SerializedName("year") var year: String? = null,
    @SerializedName("make") var make: String? = null,
    @SerializedName("model") var model: String? = null,
    @SerializedName("trim") var trim: String? = null,
    @SerializedName("fuel") var fuel: String? = null
)


