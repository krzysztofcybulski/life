package me.kcybulski.life.server

sealed interface WebMessage

data class ChangeCellsMessage(
    val generation: Int,
    val changes: List<ChangeSingleCellMessage>
): WebMessage

data class ChangeSingleCellMessage(
    val action: ChangeCellAction,
    val x: Int,
    val y: Int
)

enum class ChangeCellAction {
    DIE, BORN
}


