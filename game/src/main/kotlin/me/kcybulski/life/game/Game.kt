package me.kcybulski.life.game

class Game(
    val map: GameMap,
    val rules: Rules = Conway,
    val generation: Int = 0
) {

    fun afterGenerations(n: Int): Game = this

    fun nextGeneration(): Game = Game(
        map = map.next(rules),
        rules = rules,
        generation = generation + 1
    )

}