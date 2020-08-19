package com.smart.tomato.words.repository


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.context.annotation.Property
import java.io.File
import javax.inject.Singleton

@Singleton
internal class InMemoryWordsRepository(@Property(name = "content.path") val contentPath: String) : WordsRepository {
    override val words: List<Word> = loadWords()


    fun loadWords(): List<Word> {
        val objectMapper = ObjectMapper()
        val file = File(contentPath)

        return objectMapper.readValue(file, object : TypeReference<List<Word>>() {})
    }

}