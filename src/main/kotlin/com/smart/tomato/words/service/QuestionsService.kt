package com.smart.tomato.words.service

interface QuestionsService {
    fun getWritingQuestions(numberOfQuestions: Int) : List<WritingQuestion>
    fun getChoiceQuestions(numberOfQuestions: Int, numberOfVariants: Int = 5) : List<ChoiceQuestion>
}