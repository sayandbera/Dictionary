package com.sayanbera.dictionary.presentation

import android.content.Context

sealed class MainEvent {
    data class OnSearchClick(val context: Context) : MainEvent()
    data class OnInputWordChange(val inputWord: String) : MainEvent()
}