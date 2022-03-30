package me.kcybulski.life

import me.kcybulski.life.game.GameId

sealed interface GameCommand {

    val gameId: GameId
}

data class NextGeneration(
    override val gameId: GameId
) : GameCommand
