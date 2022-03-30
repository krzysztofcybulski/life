package me.kcybulski.life.game

class Game internal constructor(
    val id: GameId,
    private val map: GameMap,
    private val rules: Rules = Conway,
    private val generation: Int = 0,
    private val eventBus: EventBus
) {

    val livingCells = map.livingCells

    suspend fun nextGeneration(): Game {
        val (newMap, newborns, dead) = map.next(rules)
        newborns.forEach { notifyAboutNewborn(it) }
        dead.forEach { notifyAboutDeath(it) }
        notifyAboutGenerationEnd()
        return copyToNextGeneration(newMap)
    }

    private suspend fun notifyAboutNewborn(position: Position) {
        eventBus.send(CellBorn(id, generation, position))
    }

    private suspend fun notifyAboutDeath(position: Position) {
        eventBus.send(CellDie(id, generation, position))
    }
    private suspend fun notifyAboutGenerationEnd() {
        eventBus.send(GenerationEnded(id, generation))
    }

    private fun copyToNextGeneration(newMap: GameMap) = Game(
        id = id,
        map = newMap,
        rules = rules,
        generation = generation + 1,
        eventBus = eventBus
    )

}
