package me.kcybulski.life

import me.kcybulski.life.game.Game
import me.kcybulski.life.game.GameId

interface GamesRepository {

    fun find(gameId: GameId): Game?
    fun save(game: Game): Game

}
