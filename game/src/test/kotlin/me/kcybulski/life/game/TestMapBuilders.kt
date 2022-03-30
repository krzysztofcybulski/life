package me.kcybulski.life.game

import me.kcybulski.life.game.Position

fun map(vararg rows: String): Set<Position> = rows.flatMapIndexed { y, row ->
    row.mapIndexedNotNull { x, c -> c.takeUnless { c.isWhitespace() }?.let { Position(x, y) } }
}.toSet()

fun emptyMap(): Set<Position> = emptySet()
