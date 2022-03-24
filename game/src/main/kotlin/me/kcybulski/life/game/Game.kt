package me.kcybulski.life.game

class Game(
    private val map: GameMap,
    private val rules: Rules = Conway,
    private val generation: Int = 0,
    private val eventBus: EventBus
) {

    val livingCells = map.livingCells

    fun afterGenerations(n: Int): Game = this

    fun nextGeneration(): Game {
        val (newMap, newborns, dead) = map.next(rules)
        newborns.forEach(this::notifyAboutNewborn)
        dead.forEach(this::notifyAboutDeath)
        notifyAboutGenerationEnd()
        return copy(newMap)
    }

    private fun notifyAboutNewborn(position: Position) {
        eventBus.send(CellBorn(generation, position))
    }

    private fun notifyAboutDeath(position: Position) {
        eventBus.send(CellDie(generation, position))
    }
    private fun notifyAboutGenerationEnd() {
        eventBus.send(GenerationEnded(generation))
    }

    private fun copy(newMap: GameMap) = Game(
        map = newMap,
        rules = rules,
        generation = generation + 1,
        eventBus = eventBus
    )

}