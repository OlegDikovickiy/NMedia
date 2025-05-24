package ru.netology.nmadia_hw

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmadia_hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            binding.author.text = post.author
            binding.published.text = post.published
            binding.content.text = post.content
            binding.likeCount.text = formatCount(post.likes)
            binding.repostCount.text = formatCount(post.shares)
            binding.viewsCount.text = formatCount(post.views)
            binding.likeIcon.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
            )


            binding.likeIcon.setOnClickListener {
                viewModel.like()
            }

            binding.repostIcon.setOnClickListener {
                viewModel.share()
            }
        }
    }


    private fun formatCount(count: Int): String {
        return when {
            count < 1_000 -> count.toString()
            count < 10_000 -> String.format("%.1fK", count / 1_000.0).replace(".0", "")
            count < 1_000_000 -> "${count / 1_000}K"
            count < 10_000_000 -> String.format("%.1fM", count / 1_000_000.0).replace(".0", "")
            else -> "${count / 1_000_000}M"
        }
    }
}