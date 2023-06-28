package com.marcod.malabag.checkout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Checkout (
    var image:String ? = "",
    var name:String ? = "",
    var harga: String ?=""
): Parcelable