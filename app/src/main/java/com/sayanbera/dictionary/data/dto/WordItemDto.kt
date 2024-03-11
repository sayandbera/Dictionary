package com.sayanbera.dictionary.data.dto

data class WordItemDto(
    val meanings: List<MeaningDto>? = null,
    val phonetic: String? = null,
    val sourceUrls: List<String>? = null,
    val word: String? = null
)