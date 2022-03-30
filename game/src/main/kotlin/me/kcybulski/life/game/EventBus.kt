package me.kcybulski.life.game

import mu.KotlinLogging

interface EventBus {

    suspend fun send(event: GameEvent)

}

object NopEventBus: EventBus {

    private val logger = KotlinLogging.logger {}

    override suspend fun send(event: GameEvent) {
        logger.info { "Ignoring $event" }
    }

}

class HandlersEventBus(
    private val handlers: List<suspend (GameEvent) -> Unit>
): EventBus {

    override suspend fun send(event: GameEvent) {
        handlers.forEach { it(event) }
    }
}
