package com.comp304.deliciadsouza_comp304lab2_ex1.utils

import java.io.Serializable

data class HomeType(
    val address: String,
    val price: String,
    val imageResourceId: Int,
    var isSelected: Boolean = false,
    val homeType: String
) : Serializable
