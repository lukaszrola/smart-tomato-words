package com.smart.tomato.words.repository

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class Word @JsonCreator constructor(
        @JsonProperty("id") val id: Long,
        @JsonProperty("motherMeaning") val motherMeaning: String,
        @JsonProperty("foreignMeaning") val foreignMeaning: String,
        @JsonProperty("synonyms") val synonyms: List<String>)