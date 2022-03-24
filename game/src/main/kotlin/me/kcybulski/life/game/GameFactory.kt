package me.kcybulski.life.game

class GameFactory(
    private val eventBus: EventBus = NopEventBus
) {

    fun conway(firstCells: Set<Position> = emptySet()) =
        Game(
            map = ActiveOnlyGameMap(firstCells),
            rules = Conway,
            generation = 0,
            eventBus = eventBus
        )

}