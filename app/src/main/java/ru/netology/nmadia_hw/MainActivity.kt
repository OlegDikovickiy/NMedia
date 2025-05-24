package ru.netology.nmadia_hw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmadia_hw.databinding.ActivityMainBinding
import ru.netology.nmadia_hw.dto.Post

class MainActivity : AppCompatActivity() {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        published = "21 мая в 18:36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likedByMe = false,
        likes = 6397,
        shares = 5,
        views = 50
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Установить стартовые значения
        bindPost(binding)

        // Лайк
        binding.likeIcon.setOnClickListener {
            post.likedByMe = !post.likedByMe
            post.likes += if (post.likedByMe) 1 else -1
            bindPost(binding)
        }

        // Репост
        binding.repostIcon.setOnClickListener {
            post.shares += 1
            bindPost(binding)
        }

    }

    fun formatCount(count: Int): String = when {
        count < 1_000 -> count.toString()
        count < 10_000 -> {
            val thousands = count / 1_000
            val hundreds = (count % 1_000) / 100
            if (hundreds == 0) "${thousands}K"
            else "${thousands}.${hundreds}K"
        }
        count < 1_000_000 -> "${count / 1_000}K"
        count < 10_000_000 -> {
            val millions = count / 1_000_000
            val hundredThousands = (count % 1_000_000) / 100_000
            if (hundredThousands == 0) "${millions}M"
            else "${millions}.${hundredThousands}M"
        }
        else -> "${count / 1_000_000}M"
    }

    private fun bindPost(binding: ActivityMainBinding) {
        binding.author.text = post.author
        binding.published.text = post.published
        binding.content.text = post.content
        binding.likeCount.text = formatCount(post.likes)
        binding.repostCount.text = formatCount(post.shares)
        binding.viewsCount.text = formatCount(post.views)
        binding.likeIcon.setImageResource(
            if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
        )
    }
}