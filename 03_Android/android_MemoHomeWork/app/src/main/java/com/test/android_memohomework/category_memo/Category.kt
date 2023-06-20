package com.test.android_memohomework.category_memo

import android.os.Parcel
import android.os.Parcelable

class Category() : Parcelable {

    val data = TotalData()

    var categoryList = mutableListOf<String>() // 카테고리 이름
    var memoList = mutableListOf<Memo>() // 카테고리 내 메모리스트

    constructor(parcel: Parcel) : this() {
        categoryList = data.categoryMap.keys.toMutableList()
        if(data.categoryMap.containsKey(parcel.readString())){
            memoList = data.categoryMap[parcel.readString()]!!
        } else {
            // 카테고리에 저장된 메모가 아예 없을 경우 빈 리스트를 생성한다.
            // 혹은 카테고리에 해당 이름이 없을 경우.
            memoList = mutableListOf<Memo>()
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeList(categoryList)
        parcel.writeTypedList(memoList)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}