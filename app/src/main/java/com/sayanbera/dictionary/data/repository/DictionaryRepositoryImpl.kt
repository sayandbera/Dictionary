package com.sayanbera.dictionary.data.repository

import com.sayanbera.dictionary.data.mapper.toWordItem
import com.sayanbera.dictionary.data.remote.DictionaryApi
import com.sayanbera.dictionary.domain.model.WordItem
import com.sayanbera.dictionary.domain.repository.DictionaryRepository
import com.sayanbera.dictionary.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepositoryImpl @Inject constructor(
    private val api: DictionaryApi
) : DictionaryRepository {

    override suspend fun getWordResult(
        word: String
    ): Flow<Result<WordItem>> {
        return flow {
            emit(Result.Loading())

            val response = try {
                api.getWordResult(word)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("No Definitions Found!"))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Network Error"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.message ?: "Unknown Error"))
                return@flow
            }

            val wordItem = response.firstOrNull()?.toWordItem() ?: let {
                emit(Result.Error("No data found for '$word'"))
                return@flow
            }

            emit(Result.Success(wordItem))
        }
    }
}
