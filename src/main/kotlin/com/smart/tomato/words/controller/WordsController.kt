package com.smart.tomato.words.controller

import com.smart.tomato.words.service.ChoiceQuestion
import com.smart.tomato.words.service.QuestionsService
import com.smart.tomato.words.service.WritingQuestion
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/words")
@Produces(MediaType.APPLICATION_JSON)
class WordsController(private val questionsService: QuestionsService) {


    @Get("/writingQuestions")
    fun getWritingQuestions(@QueryValue(defaultValue = "5") numberOfQuestions: Int): List<WritingQuestion> =
            questionsService.getWritingQuestions(numberOfQuestions)

    @Get("/choiceQuestions")
    fun getChoiceQuestions(
            @QueryValue(defaultValue = "5") numberOfQuestions: Int,
            @QueryValue(defaultValue = "3") numberOfVariants: Int): List<ChoiceQuestion> =
            questionsService.getChoiceQuestions(
                    numberOfQuestions = numberOfQuestions,
                    numberOfVariants = numberOfVariants)

}