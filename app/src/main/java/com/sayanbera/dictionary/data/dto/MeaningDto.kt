package com.sayanbera.dictionary.data.dto

data class MeaningDto(
    val definitions: List<DefinitionDto>? = null,
    val partOfSpeech: String? = null,
    val synonyms: List<String>? = null
)