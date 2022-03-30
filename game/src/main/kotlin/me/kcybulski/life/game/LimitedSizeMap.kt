package me.kcybulski.life.game

import kotlin.math.pow
import kotlin.math.sqrt

internal class LimitedSizeMap(
    val map: GameMap,
    val radius: Int = 1000
) : GameMap {

    override val livingCells: Set<Position> = map.livingCells

    override fun bornCell(position: Position) = update(map.bornCell(position))

    override fun killCell(position: Position) = update(map.killCell(position))

    override fun next(rules: Rules): MapUpdated = update(map.next(rules))

    private fun update(mapUpdated: MapUpdated) = mapUpdated
        .let { MapUpdated(LimitedSizeMap(it.map, radius), it.newborns.filter { it.distance <= radius }.toSet(), it.dead) }

    private val Position.distance
        get() = sqrt(x.toDouble().pow(2) + y.toDouble().pow(2)).toInt()

}
