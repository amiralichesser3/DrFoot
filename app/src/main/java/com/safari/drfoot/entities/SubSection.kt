package com.safari.drfoot.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
class SubSection(@PrimaryKey val id: Int,
                 val name: String,
                 val userId: Int,
                 val isLocked: Boolean,
                 val image: String?,
                 val sectionId: Int,
                 val text: String?,
                 val extraImage: String?,
                 val hint: String?,
                 val hint2: String?,
                 val hint3: String?,
                 val hintImage: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(userId)
        parcel.writeByte(if (isLocked) 1 else 0)
        parcel.writeString(image)
        parcel.writeInt(sectionId)
        parcel.writeString(text)
        parcel.writeString(extraImage)
        parcel.writeString(hint)
        parcel.writeString(hint2)
        parcel.writeString(hint3)
        parcel.writeString(hintImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubSection> {
        override fun createFromParcel(parcel: Parcel): SubSection {
            return SubSection(parcel)
        }

        override fun newArray(size: Int): Array<SubSection?> {
            return arrayOfNulls(size)
        }
    }
}