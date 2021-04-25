package com.akuarintar.data.model.question

import com.um.puak.quiz.model.Quiz


data class Question(
        val id: Int,
        val answers: List<Quiz>,
        val correct: Quiz,
        val questionImage: Int,
        val questionText: String? = null
)

