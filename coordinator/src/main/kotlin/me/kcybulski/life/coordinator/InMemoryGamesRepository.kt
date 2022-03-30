package me.kcybulski.life.coordinator

import me.kcybulski.life.game.Game
import me.kcybulski.life.game.GameId

class InMemoryGamesRepository: GamesRepository {

    private val games = mutableMapOf<GameId, Game>()

    override suspend fun find(gameId: GameId): Game? = games[gameId]

    override suspend fun save(game: Game): Game {
        games[game.id] = game
        return game
    }
}
