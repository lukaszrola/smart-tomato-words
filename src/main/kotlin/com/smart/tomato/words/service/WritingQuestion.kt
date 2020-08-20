package com.smart.tomato.words.service

import com.fasterxml.jackson.annotation.JsonAutoDetect

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class WritingQuestion constructor(val question: String, val answers: List<String>)