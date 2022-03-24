package me.kcybulski.life.game

interface GameMap {

    val livingCells: Set<Position>

    fun bornCell(position: Position): GameMap
    fun killCell(position: Position): GameMap

    fun next(rules: Rules): GameMap

}
