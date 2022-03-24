package me.kcybulski.life.game

class ActiveOnlyGameMap(
    override val livingCells: Set<Position> = emptySet()
) : GameMap {

    override fun bornCell(position: Position) = ActiveOnlyGameMap(
        livingCells = livingCells + position
    )

    override fun killCell(position: Position) = ActiveOnlyGameMap(
        livingCells = livingCells - position
    )

    override fun next(rules: Rules): GameMap {
        val stillLiving = livingCells.filter { rules.willSurvive(countFriends(it)) }.toSet()
        val newborns = (livingCells.flatMap(Position::friends) - livingCells)
            .filter { rules.willBorn(countFriends(it)) }
            .toSet()
        return ActiveOnlyGameMap(
            livingCells = stillLiving + newborns
        )
    }

    private fun countFriends(position: Position) =
        position
            .friends
            .count { it in livingCells }
}