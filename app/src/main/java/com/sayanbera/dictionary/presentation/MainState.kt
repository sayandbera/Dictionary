package com.sayanbera.dictionary.presentation

import com.sayanbera.dictionary.domain.model.WordItem

data class MainState(
    val inputWord: String = "",
    val isLoading: Boolean = false,
    val wordItem: WordItem? = null
)