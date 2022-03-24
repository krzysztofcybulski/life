package me.kcybulski.life.game

import mu.KotlinLogging

interface EventBus {

    fun send(event: Any)

}

object NopEventBus: EventBus {

    private val logger = KotlinLogging.logger {}

    override fun send(event: Any) {
        logger.info { "Ignoring $event" }
    }

}