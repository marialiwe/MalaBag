package com.marcod.malabag.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bag (
    var image:String ? = "",
    var name:String ? = "",
    var price:String ? = "",
    var colors:String ? = "",
    var size:String ? = ""
) : Parcelable