package com.sayanbera.dictionary.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayanbera.dictionary.domain.repository.DictionaryRepository
import com.sayanbera.dictionary.util.Result
import com.sayanbera.dictionary.util.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DictionaryRepository
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnSearchClick -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    loadWordResult(
                        context = event.context,
                        word = mainState.value.inputWord
                    )
                }
            }

            is MainEvent.OnInputWordChange -> {
                _mainState.update {
                    it.copy(inputWord = event.inputWord)
                }
            }
        }
    }

    private fun loadWordResult(context: Context, word: String) {
        viewModelScope.launch {
            repository.getWordResult(word = word)
                .flowOn(Dispatchers.IO)
                .collect { wordItemResult ->
                when (wordItemResult) {
                    is Result.Error -> {
                        _mainState.update {
                            it.copy(isLoading = false)
                        }
                        showToast(message = wordItemResult.message ?: "Error!", context = context)
                    }
                    is Result.Loading -> {
                        _mainState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Result.Success -> {
                        _mainState.update {
                            it.copy(
                                isLoading = false,
                                wordItem = wordItemResult.data
                            )
                        }
                    }
                }
            }
        }
    }
}