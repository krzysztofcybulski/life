package me.kcybulski.life.game

data class Position(val x: Int, val y: Int) {

    val friends: Set<Position>
        get() = (x - 1..x + 1).flatMap { x ->
            (y - 1..y + 1).map { y ->
                Position(x, y)
            }
        }.toSet() - this
}

infix fun Int.x(y: Int) = Position(this, y)
