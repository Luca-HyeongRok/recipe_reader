package com.example.myapplication.recipereader.util

private val INITIALS = charArrayOf(
    'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
    'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
)

fun extractInitials(text: String): String {
    val builder = StringBuilder()
    for (ch in text) {
        builder.append(toInitial(ch))
    }
    return builder.toString()
}

fun matchesInitials(name: String, query: String): Boolean {
    val trimmedQuery = query.trim().replace(" ", "")
    if (trimmedQuery.isEmpty()) return true
    val normalizedName = name.trim().replace(" ", "")
    if (normalizedName.contains(trimmedQuery, ignoreCase = true)) return true
    val initials = extractInitials(normalizedName)
    return initials.contains(trimmedQuery, ignoreCase = true)
}

private fun toInitial(ch: Char): Char {
    val base = ch.code - 0xAC00
    if (base < 0 || base >= 11172) return ch
    val index = base / 588
    return INITIALS[index]
}
