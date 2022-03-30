package me.kcybulski.life.game

abstract class Rules(
    val livesWithFriends: Set<Int>,
    val bornFromFriends: Set<Int>
) {

    fun willSurvive(friends: Int): Boolean = friends in livesWithFriends

    fun willBorn(friends: Int): Boolean = friends in bornFromFriends

}

object Conway: Rules(setOf(2, 3), setOf(3))
