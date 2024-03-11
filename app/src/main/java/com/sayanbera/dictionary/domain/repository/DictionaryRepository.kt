package com.sayanbera.dictionary.domain.repository

import com.sayanbera.dictionary.domain.model.WordItem
import com.sayanbera.dictionary.util.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getWordResult(
        word: String
    ): Flow<Result<WordItem>>
}