package com.test.android68_study

import java.io.Serializable

data class Info(val name: String, val age: Int, val kor: Int) : Serializable

var infoList = mutableListOf<Info>()

