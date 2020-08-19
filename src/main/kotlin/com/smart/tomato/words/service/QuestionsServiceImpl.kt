package com.smart.tomato.words.service

import com.smart.tomato.words.repository.WordsRepository
import java.lang.Integer.min
import javax.inject.Singleton

@Singleton
internal class QuestionsServiceImpl(private val wordsRepository: WordsRepository) : QuestionsService {

    override fun getWritingQuestions(numberOfQuestions: Int): List<WritingQuestion> {
        val numberOfQuestionsToReturn = min(wordsRepository.words.size, numberOfQuestions)
        return wordsRepository.words
                .shuffled()
                .subList(0, numberOfQuestionsToReturn)
                .map {
                    WritingQuestion(
                            question = it.motherMeaning,
                            answers = listOf(it.foreignMeaning) + it.synonyms
                    )
                }
    }
}