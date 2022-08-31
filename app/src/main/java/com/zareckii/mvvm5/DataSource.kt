package com.zareckii.mvvm5

interface DataSource {

    fun saveInt(key: String, value: Int)

    fun getInt(key: String): Int
}