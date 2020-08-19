package com.smart.tomato.words.controller

import com.smart.tomato.words.service.QuestionsService
import com.smart.tomato.words.service.WritingQuestion
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/words")
class WordsController (private val questionsService: QuestionsService){


    @Get("/writingQuestions")
    @Produces(MediaType.APPLICATION_JSON)
    fun getWritingQuestions(@QueryValue(defaultValue = "5") numberOfQuestions: Int) : List<WritingQuestion> {
        return questionsService.getWritingQuestions(numberOfQuestions) }
}