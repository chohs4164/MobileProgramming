package com.example.dweek04a.model

data class Item(
    val content: String,
    val time: String,
    var status: TodoStatus = TodoStatus.PENDING
)