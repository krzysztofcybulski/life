package me.kcybulski.life

import me.kcybulski.life.game.Game
import me.kcybulski.life.game.GameMap

fun conway(
    map: GameMap = emptyMap()
): Game = Game(map)