package com.smart.tomato.words.service

import com.smart.tomato.words.repository.Word
import com.smart.tomato.words.repository.WordsRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize

internal class QuestionsServiceTest : BehaviorSpec({
    val wordsRepository: WordsRepository = mockk()
    val questionsService: QuestionsService = QuestionsServiceImpl(wordsRepository)

    given("Words repository with one item") {
        every{
            getMock(wordsRepository).words
        } answers {
            listOf(
                    Word(
                            id = 1,
                            motherMeaning = "pies",
                            foreignMeaning = "dog",
                            synonyms = listOf("first synonym")
                    )
            )
        }

        `when`("Requested about one item") {
            val result = questionsService.getWritingQuestions(1)

            then("Should return expected writing question") {
                result shouldHaveSize 1
                result[0].question shouldBeEqualTo  "pies"
                result[0].answers shouldContainAll listOf("dog", "first synonym")
            }
        }
    }


    given("Words repository with three items") {
        every{
            getMock(wordsRepository).words
        } answers {
            listOf(
                    Word(
                            id = 1,
                            motherMeaning = "pies",
                            foreignMeaning = "dog",
                            synonyms = listOf()
                    ),
                    Word(
                            id = 2,
                            motherMeaning = "kot",
                            foreignMeaning = "cat",
                            synonyms = listOf()
                    ),
                    Word(
                            id = 3,
                            motherMeaning = "dom",
                            foreignMeaning = "house",
                            synonyms = listOf()
                    )
            )
        }

        `when`("Requested about two writing questions") {
            val result = questionsService.getWritingQuestions(2)

            then("Should return two random writing questions") {
                result shouldHaveSize 2
            }
        }

        `when`("Requested about four questions") {
            val result = questionsService.getWritingQuestions(4)

            then("Should return three writing questions") {
                result shouldHaveSize 3
            }
        }
    }
})