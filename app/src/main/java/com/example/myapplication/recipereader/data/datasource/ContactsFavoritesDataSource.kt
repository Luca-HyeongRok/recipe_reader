package com.example.myapplication.recipereader.data.datasource

class ContactsFavoritesDataSource(
    private val dao: FavoriteContactDao
) {
    suspend fun getFavoriteIds(): Set<String> = dao.getAllIds().toSet()

    suspend fun setFavorite(contactId: String, isFavorite: Boolean) {
        if (isFavorite) {
            dao.insert(FavoriteContactEntity(contactId))
        } else {
            dao.delete(contactId)
        }
    }
}
