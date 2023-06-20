package com.test.android_memohomework.category_memo

class TotalData {
    // 전체적인 데이터를 저장 용도
    // key : 카테고리 이름
    // value : 해당 카테고리가 가지고 있는 메모리스트
    var categoryMap = LinkedHashMap<String, MutableList<Memo>>()
}