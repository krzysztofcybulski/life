package me.kcybulski.life

import me.kcybulski.life.game.EventBus
import me.kcybulski.life.game.Game
import me.kcybulski.life.game.GameFactory
import me.kcybulski.life.game.NopEventBus
import me.kcybulski.life.game.Position


suspend fun conway(
    map: Set<Position> = emptyMap(),
    eventBus: EventBus = NopEventBus
): Game = GameFactory(eventBus).conway(map)
