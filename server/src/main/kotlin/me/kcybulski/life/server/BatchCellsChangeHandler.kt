package me.kcybulski.life.server

import kotlinx.coroutines.channels.Channel
import me.kcybulski.life.game.CellBorn
import me.kcybulski.life.game.CellDie
import me.kcybulski.life.game.GameEvent
import me.kcybulski.life.game.GenerationEnded
import me.kcybulski.life.server.ChangeCellAction.BORN
import me.kcybulski.life.server.ChangeCellAction.DIE

@FunctionalInterface
class BatchCellsChangeHandler(
    private val channel: Channel<WebMessage>
) {

    private val generationEvents: MutableSet<GameEvent> = mutableSetOf()

    suspend fun onEvent(gameEvent: GameEvent) {
        when(gameEvent) {
            is GenerationEnded -> {
                channel.send(
                    ChangeCellsMessage(
                        generation = gameEvent.generation,
                        changes = generationEvents.mapNotNull { it.asMessage() }
                    )
                )
                generationEvents.clear()
            }
            else -> generationEvents += gameEvent
        }
    }

    private fun GameEvent.asMessage() = when (this) {
        is CellBorn -> ChangeSingleCellMessage(BORN, position.x, position.y)
        is CellDie -> ChangeSingleCellMessage(DIE, position.x, position.y)
        else -> null
    }

}
