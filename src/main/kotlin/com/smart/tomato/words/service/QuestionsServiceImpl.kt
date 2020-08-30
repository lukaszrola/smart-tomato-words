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

    override fun getChoiceQuestions(numberOfQuestions: Int, numberOfVariants: Int): List<ChoiceQuestion> {
        val numberOfQuestionsToReturn = min(wordsRepository.words.size, numberOfQuestions)
        return wordsRepository.words
                .shuffled()
                .subList(0, numberOfQuestionsToReturn)
                .map {
                    ChoiceQuestion(
                            question = it.motherMeaning,
                            correctAnswer = it.foreignMeaning,
                            variants = calculateVariants(it.foreignMeaning, numberOfVariants)
                    )
                }
    }

    private fun calculateVariants(correctAnswer: String, numberOfVariants: Int): List<String> {
        val invalidVariants = wordsRepository.words
                .shuffled()
                .asSequence()
                .map { it.foreignMeaning }
                .filter { it != correctAnswer }
                .take(numberOfVariants)
                .toList()

        return (invalidVariants + correctAnswer).shuffled()
    }
}