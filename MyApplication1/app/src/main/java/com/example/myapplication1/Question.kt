package com.example.myapplication1

data class Question(
    val question: String,
    val options: Array<String>,
    val correctAnswerIndex: Int
)