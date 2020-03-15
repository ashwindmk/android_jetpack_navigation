package com.ashwin.android.jetpacknavigation

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

data class Money(val amount : BigDecimal) : Parcelable {
    constructor(parcel: Parcel) : this(BigDecimal(parcel.readString()))

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(amount.toString())
    }

    override fun describeContents(): Int {
        return amount.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<Money> {
        override fun createFromParcel(parcel: Parcel): Money {
            return Money(parcel)
        }

        override fun newArray(size: Int): Array<Money?> {
            return arrayOfNulls(size)
        }
    }
}
