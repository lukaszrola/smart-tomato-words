package com.smart.tomato.words.controller


import com.smart.tomato.words.service.ChoiceQuestion
import com.smart.tomato.words.service.QuestionsService
import com.smart.tomato.words.service.QuestionsServiceImpl
import com.smart.tomato.words.service.WritingQuestion
import io.kotest.core.spec.style.AnnotationSpec
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotest.MicronautKotestExtension.getMock
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test


@MicronautTest
class WordsControllerTest(@Client("/") private val client: RxHttpClient, private val questionsService: QuestionsService) : AnnotationSpec() {

    private val expectedWritingQuestions = listOf(
            WritingQuestion(
                    question = "First Question",
                    answers = listOf("first answer")
            ),
            WritingQuestion(
                    question = "Second question",
                    answers = listOf("answer to second question")
            )
    )
    private val expectedChoiceQuestions = listOf(
            ChoiceQuestion(
                    question = "First question",
                    correctAnswer = "correct answer",
                    variants = listOf("correct answer", "some invalid variant")
            ),
            ChoiceQuestion(
                    question = "Second question",
                    correctAnswer = "second correct answer",
                    variants = listOf("second correct answer", "some invalid variant")
            )
    )
    private val expectedNumberOfQuestions = 2
    private val expectedNumberOfVariants = 2

    @Test
    internal fun shouldReturnWritingQuestions() {
        val questionsServiceMock = getMock(questionsService)
        every {
            questionsServiceMock.getWritingQuestions(expectedNumberOfQuestions)
        } answers {
            expectedWritingQuestions
        }

        val request: HttpRequest<Any> = HttpRequest.GET("/words/writingQuestions?numberOfQuestions=$expectedNumberOfQuestions")
        val body = client.toBlocking().retrieve(request, Argument.listOf(WritingQuestion::class.java))

        verify(exactly = 1) { questionsServiceMock.getWritingQuestions(expectedNumberOfQuestions) }
        body shouldBeEqualTo expectedWritingQuestions
    }

    @Test
    internal fun shouldReturnChoiceQuestions() {
        val questionsServiceMock = getMock(questionsService)
        every {
            questionsServiceMock.getChoiceQuestions(
                    numberOfQuestions = expectedNumberOfQuestions,
                    numberOfVariants = expectedNumberOfVariants
            )
        } answers {
            expectedChoiceQuestions
        }

        val request: HttpRequest<Any> = HttpRequest.GET(
                "/words/choiceQuestions?" +
                        "numberOfQuestions=$expectedNumberOfQuestions" +
                        "&numberOfVariants=$expectedNumberOfVariants"
        )
        val body = client.toBlocking().retrieve(request, Argument.listOf(ChoiceQuestion::class.java))

        verify(exactly = 1) {
            questionsServiceMock.getChoiceQuestions(expectedNumberOfQuestions, expectedNumberOfVariants)
        }
        body shouldBeEqualTo expectedChoiceQuestions
    }

    @MockBean(QuestionsServiceImpl::class)
    internal fun questionService(): QuestionsService {
        return mockk()
    }
}