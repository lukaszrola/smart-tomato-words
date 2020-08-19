package com.smart.tomato.words.repository

data class Word(val id: Long = 0, val motherMeaning: String = "", val foreignMeaning: String = "", val synonyms: List<String> = listOf())