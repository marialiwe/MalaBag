package com.marcod.malabag.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bags (
    var colors:String ? = "",
    var size:String ? = ""
) : Parcelable