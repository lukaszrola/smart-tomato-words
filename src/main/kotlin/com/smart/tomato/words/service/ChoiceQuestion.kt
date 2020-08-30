package com.smart.tomato.words.service

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ChoiceQuestion @JsonCreator constructor(
        @JsonProperty("question") val question: String,
        @JsonProperty("correctAnswer") val correctAnswer: String,
        @JsonProperty("variants") val variants: List<String>
)
