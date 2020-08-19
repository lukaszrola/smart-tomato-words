package com.smart.tomato.words.controller


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


@MicronautTest
class WordsControllerTest(@Client("/") private val client: RxHttpClient, private val questionsService: QuestionsService) : AnnotationSpec() {

    private val EXPECTED_QUESTIONS = listOf(
            WritingQuestion(
                    question = "First Question",
                    answers = listOf("first answer")
            ),
            WritingQuestion(
                    question = "Second question",
                    answers = listOf("answer to second question")
            )
    )

    @Test
    fun shouldReturnWritingQuestions() {
        val questionsServiceMock = getMock(questionsService)
        every {
            questionsServiceMock.getWritingQuestions(any())
        } answers {
            EXPECTED_QUESTIONS
        }

        val request: HttpRequest<Any> = HttpRequest.GET("/words/writingQuestions")
        val body = client.toBlocking().retrieve(request, Argument.listOf(WritingQuestion::class.java))

        verify(exactly = 1) { questionsServiceMock.getWritingQuestions(any()) }
        body shouldBeEqualTo EXPECTED_QUESTIONS
    }

    @MockBean(QuestionsServiceImpl::class)
    fun questionService(): QuestionsService {
        return mockk()
    }
}