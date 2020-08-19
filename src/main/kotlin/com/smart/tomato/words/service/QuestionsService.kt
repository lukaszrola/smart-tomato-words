package com.smart.tomato.words.service

interface QuestionsService {
    fun getWritingQuestions(numberOfQuestions: Int) : List<WritingQuestion>
}