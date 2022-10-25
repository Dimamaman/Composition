package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {
    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseNumber()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    retryGame()
//                }
//            })
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswer.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.smilling
        } else {
            R.drawable.sad
        }
    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    private fun parseNumber() {
        requireArguments().getParcelable<GameResult>(NUMBER)?.let {
            gameResult = it
        }
    }

    companion object {
        const val NUMBER = "number"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NUMBER, gameResult)
                }
            }
        }
    }
}