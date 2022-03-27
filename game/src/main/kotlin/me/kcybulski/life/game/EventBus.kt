package me.kcybulski.life.game

import mu.KotlinLogging

interface EventBus {

    suspend fun send(event: Any)

}

object NopEventBus: EventBus {

    private val logger = KotlinLogging.logger {}

    override suspend fun send(event: Any) {
        logger.info { "Ignoring $event" }
    }

}

class HandlersEventBus(
    private val handlers: List<suspend (Any) -> Unit>
): EventBus {

    override suspend fun send(event: Any) {
        handlers.forEach { it(event) }
    }
}
