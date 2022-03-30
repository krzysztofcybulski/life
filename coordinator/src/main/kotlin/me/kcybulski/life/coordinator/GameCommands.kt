package me.kcybulski.life.coordinator

import me.kcybulski.life.game.GameId

sealed interface GameCommand {

    val gameId: GameId
}

data class NextGeneration(
    override val gameId: GameId
) : GameCommand
