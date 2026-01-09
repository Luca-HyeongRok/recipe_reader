package com.example.myapplication.recipereader.domain.model

data class Recipe(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val chef: String,
    val time: String,
    val rating: Double
)
