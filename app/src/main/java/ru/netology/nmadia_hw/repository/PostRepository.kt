package ru.netology.nmadia_hw.repository

import androidx.lifecycle.LiveData
import ru.netology.nmadia_hw.dto.Post

interface PostRepository {
    fun get():LiveData<Post>
    fun like()
    fun share()
}