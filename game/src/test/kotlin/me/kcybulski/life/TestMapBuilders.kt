package me.kcybulski.life

import io.kotest.matchers.shouldBe
import me.kcybulski.life.game.ActiveOnlyGameMap
import me.kcybulski.life.game.GameMap
import me.kcybulski.life.game.Position

fun map(vararg rows: String): GameMap = rows.flatMapIndexed { y, row ->
    row.mapIndexedNotNull { x, c -> c.takeUnless { c.isWhitespace() }?.let { Position(x, y) } }
}.let { ActiveOnlyGameMap(it.toSet()) }

fun emptyMap(): GameMap = ActiveOnlyGameMap()

infix fun GameMap.shouldBe(second: GameMap) = livingCells shouldBe second.livingCells