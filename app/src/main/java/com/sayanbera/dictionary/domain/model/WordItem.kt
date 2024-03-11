package com.sayanbera.dictionary.domain.model

data class WordItem(
    val meanings: List<Meaning>,
    val phonetic: String,
    val sourceUrls: List<String>,
    val word: String
)
