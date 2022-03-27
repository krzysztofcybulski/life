package me.kcybulski.life.game

import java.util.UUID.randomUUID

class GameFactory(
    private val eventBus: EventBus = NopEventBus
) {

    fun conway(firstCells: Set<Position> = emptySet()) =
        Game(
            id = GameId(randomUUID().toString()),
            map = ActiveOnlyGameMap(firstCells),
            rules = Conway,
            generation = 0,
            eventBus = eventBus
        )

}
