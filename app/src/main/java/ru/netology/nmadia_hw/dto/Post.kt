package ru.netology.nmadia_hw.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean,
    var likes: Int,
    var shares: Int,
    var views: Int
)
