package me.kcybulski.life.game

interface GameEvent {

    val id: GameId

}

data class GameStarted(
    override val id: GameId,
    val cells: Set<Position>
): GameEvent

data class CellBorn(
    override val id: GameId,
    val generation: Int,
    val position: Position
): GameEvent

data class CellDie(
    override val id: GameId,
    val generation: Int,
    val position: Position
): GameEvent

data class GenerationEnded(
    override val id: GameId,
    val generation: Int
): GameEvent
