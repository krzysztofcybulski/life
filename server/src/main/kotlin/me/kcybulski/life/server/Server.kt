package me.kcybulski.life.server

import ratpack.func.Action
import ratpack.handling.Chain
import ratpack.server.RatpackServer

class Server(
) {

    private val ratpackServer: RatpackServer = RatpackServer.of { server ->
        server
            .serverConfig { config ->
                config.threads(1)
            }
            .handlers(api())
    }

    fun start() = ratpackServer.start()

    fun stop() = ratpackServer.stop()

    private fun api(): Action<Chain> = Action { chain: Chain ->
        chain
            .get { ctx ->
                ctx.render("Hello world")
            }
    }

}
