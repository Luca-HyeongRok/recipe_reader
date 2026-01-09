package com.example.myapplication.recipereader.data.datasource

import com.example.myapplication.recipereader.domain.model.Recipe

class RecipeMockDataSource {

    fun getAllRecipes(): List<Recipe> = listOf(
        Recipe(
            id = 2,
            name = "Spaghetti Carbonara",
            imageUrl = "https://cdn.pixabay.com/photo/2017/05/07/08/56/pasta-2291908_1280.jpg",
            chef = "Chef John",
            time = "20 min",
            rating = 4.5
        ),
        Recipe(
            id = 3,
            name = "Grilled Chicken",
            imageUrl = "https://cdn.pixabay.com/photo/2016/11/18/15/07/chicken-1837991_1280.jpg",
            chef = "Mark Kelvin",
            time = "30 min",
            rating = 4.2
        ),
        Recipe(
            id = 6,
            name = "Beef Wellington",
            imageUrl = "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
            chef = "Gordon Ramsay",
            time = "45 min",
            rating = 5.0
        )
    )
}
