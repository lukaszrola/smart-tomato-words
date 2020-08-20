package com.smart.tomato.words.service

import com.fasterxml.jackson.annotation.JsonProperty

data class WritingQuestion(
        @JsonProperty val question: String,
        @JsonProperty val answers: List<String>
)