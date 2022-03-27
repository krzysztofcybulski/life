package me.kcybulski.life.game

internal class ActiveOnlyGameMap(
    override val livingCells: Set<Position> = emptySet()
) : GameMap {

    override fun bornCell(position: Position) =
        if(position !in livingCells)
            updated(newborns = setOf(position))
        else
            updated()

    override fun killCell(position: Position) =
        if(position in livingCells)
            updated(dead = setOf(position))
        else
            updated()

    override fun next(rules: Rules): MapUpdated {
        val dead = livingCells.filterNot { rules.willSurvive(countFriends(it)) }.toSet()
        val newborns = (livingCells.flatMap(Position::friends) - livingCells)
            .filter { rules.willBorn(countFriends(it)) }
            .toSet()
        return updated(
            newborns = newborns,
            dead = dead
        )
    }

    private fun updated(newborns: Set<Position> = emptySet(), dead: Set<Position> = emptySet()) =
        MapUpdated(
            map = ActiveOnlyGameMap(livingCells - dead + newborns),
            newborns = newborns,
            dead = dead
        )

    private fun countFriends(position: Position) =
        position
            .friends
            .count { it in livingCells }
}
