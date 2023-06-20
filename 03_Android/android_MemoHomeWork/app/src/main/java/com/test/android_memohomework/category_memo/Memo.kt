package com.test.android_memohomework.category_memo

import android.os.Parcel
import android.os.Parcelable

class Memo() : Parcelable {
    lateinit var title: String // 메모의 제목
    lateinit var contents: String // 메모의 내용
    constructor(parcel: Parcel) : this() {
        title = parcel.readString()!!
        contents = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(contents)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Memo> {
        override fun createFromParcel(parcel: Parcel): Memo {
            return Memo(parcel)
        }

        override fun newArray(size: Int): Array<Memo?> {
            return arrayOfNulls(size)
        }
    }
}