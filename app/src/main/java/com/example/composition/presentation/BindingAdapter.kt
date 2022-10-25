package com.example.composition.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R

@BindingAdapter("requiredAnswer")
fun bindRequiredAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}