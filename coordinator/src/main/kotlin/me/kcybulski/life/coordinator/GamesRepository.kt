package me.kcybulski.life.coordinator

import me.kcybulski.life.game.Game
import me.kcybulski.life.game.GameId

interface GamesRepository {

    suspend fun find(gameId: GameId): Game?
    suspend fun save(game: Game): Game

}
