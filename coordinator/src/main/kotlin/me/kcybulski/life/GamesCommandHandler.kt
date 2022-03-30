package me.kcybulski.life

import me.kcybulski.life.game.Game
import me.kcybulski.life.game.GameId
import mu.KotlinLogging

class GamesCommandHandler(
    private val repository: GamesRepository
) {

    private val logger = KotlinLogging.logger {}

    suspend fun handle(command: GameCommand) = when (command) {
        is NextGeneration -> runOn(command.gameId, Game::nextGeneration)
    }

    private suspend fun runOn(gameId: GameId, command: suspend (Game) -> Game) =
        repository.find(gameId)
            ?.run {
                repository.save(command(this))
            }
            ?: logger.warn { "Ignoring command, as couldn't find $gameId game" }

}
