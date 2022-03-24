package me.kcybulski.life.game

data class CellBorn(
    val generation: Int,
    val position: Position
)

data class CellDie(
    val generation: Int,
    val position: Position
)

data class GenerationEnded(
    val generation: Int
)