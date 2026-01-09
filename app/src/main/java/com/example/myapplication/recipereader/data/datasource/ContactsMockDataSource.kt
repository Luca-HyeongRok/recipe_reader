package com.example.myapplication.recipereader.data.datasource

import com.example.myapplication.recipereader.domain.model.Contact

class ContactsMockDataSource {

    fun getContacts(): List<Contact> = listOf(
        Contact(id = "1", name = "김민지", phoneNumber = "010-1234-5678", isFavorite = false),
        Contact(id = "2", name = "박철수", phoneNumber = "010-2345-6789", isFavorite = false),
        Contact(id = "3", name = "이영희", phoneNumber = "010-3456-7890", isFavorite = false),
        Contact(id = "4", name = "최지훈", phoneNumber = "010-4567-8901", isFavorite = false),
        Contact(id = "5", name = "정수빈", phoneNumber = "010-5678-9012", isFavorite = false),
        Contact(id = "6", name = "한지민", phoneNumber = "010-6789-0123", isFavorite = false),
        Contact(id = "7", name = "오현우", phoneNumber = "010-7890-1234", isFavorite = false),
        Contact(id = "8", name = "윤서연", phoneNumber = "010-8901-2345", isFavorite = false),
        Contact(id = "9", name = "홍길동", phoneNumber = "010-9012-3456", isFavorite = false),
        Contact(id = "10", name = "강다은", phoneNumber = "010-0123-4567", isFavorite = false),
        Contact(id = "11", name = "배서준", phoneNumber = "010-1122-3344", isFavorite = false),
        Contact(id = "12", name = "조유진", phoneNumber = "010-2233-4455", isFavorite = false)
    )
}
