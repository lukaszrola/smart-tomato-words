package com.smart.tomato.words.service

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class WritingQuestion @JsonCreator constructor(@JsonProperty("question")val question: String, @JsonProperty("answers") val answers: List<String>)