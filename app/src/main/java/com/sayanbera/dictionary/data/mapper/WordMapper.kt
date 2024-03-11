package com.sayanbera.dictionary.data.mapper

import com.sayanbera.dictionary.data.dto.DefinitionDto
import com.sayanbera.dictionary.data.dto.MeaningDto
import com.sayanbera.dictionary.data.dto.WordItemDto
import com.sayanbera.dictionary.domain.model.Definition
import com.sayanbera.dictionary.domain.model.Meaning
import com.sayanbera.dictionary.domain.model.WordItem

fun WordItemDto.toWordItem(): WordItem = WordItem(
    meanings = meanings?.map { it.toMeaning() } ?: emptyList(),
    phonetic = phonetic ?: "",
    sourceUrls = sourceUrls?.map { it } ?: emptyList(),
    word = word ?: ""
)

private fun MeaningDto.toMeaning() = Meaning(
    definitions = definitions?.map { it.toDefinition() } ?: emptyList(),
    partOfSpeech = partOfSpeech ?: "",
    synonyms = synonyms?.map { it } ?: emptyList()
)

private fun DefinitionDto.toDefinition() = Definition(
    definition = definition ?: "",
    example = example ?: ""
)