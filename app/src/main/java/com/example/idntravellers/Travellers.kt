package com.example.idntravellers

import android.os.Parcel
import android.os.Parcelable

data class Travellers(
    val name: String,
    val description: String,
    val shortDescription: String, // Menambahkan shortDescription
    val photo: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "", // Membaca shortDescription dari Parcel
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(shortDescription) // Menulis shortDescription ke Parcel
        parcel.writeInt(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Travellers> {
        override fun createFromParcel(parcel: Parcel): Travellers {
            return Travellers(parcel)
        }

        override fun newArray(size: Int): Array<Travellers?> {
            return arrayOfNulls(size)
        }
    }
}
