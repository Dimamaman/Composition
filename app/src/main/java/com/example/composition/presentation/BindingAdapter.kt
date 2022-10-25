package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswer")
fun bindRequiredAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun scoreAnswers(textView: TextView, countOfRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        countOfRightAnswers
    )
}
@BindingAdapter("requiredPercentage")
fun requiredPercentage(textView: TextView, minPercentOfRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        minPercentOfRightAnswers
    )
}

@BindingAdapter("scorePercentage")
fun scorePercentage(textView: TextView,gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultEmoji")
fun resultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.smilling
    } else {
        R.drawable.sad
    }
}