package me.kcybulski.life.game

interface GameMap {

    val livingCells: Set<Position>

    fun bornCell(position: Position): MapUpdated
    fun killCell(position: Position): MapUpdated

    fun next(rules: Rules): MapUpdated

}

data class MapUpdated(
    val map: GameMap,
    val newborns: Set<Position>,
    val dead: Set<Position>
)
