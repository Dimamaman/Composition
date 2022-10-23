package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository

object GameRepositoryImpl: GameRepository {
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        TODO("Not yet implemented")
    }

    override fun getGameSettings(level: Level): GameSettings {
        TODO("Not yet implemented")
    }
}