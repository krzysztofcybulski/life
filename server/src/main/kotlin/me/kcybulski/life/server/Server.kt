package me.kcybulski.life.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import me.kcybulski.life.game.Position
import ratpack.func.Action
import ratpack.handling.Chain
import ratpack.server.RatpackServer
import ratpack.websocket.WebSockets

class Server(
    private val messagesChannel: Channel<WebMessage>,
    private val livingCells: suspend () -> Set<Position>,
    private val coroutine: CoroutineScope,
    private val objectMapper: ObjectMapper = jacksonObjectMapper()
) {

    private val ratpackServer = RatpackServer.of { server ->
        server
            .serverConfig { config ->
                config.threads(1)
            }
            .handlers(defineApi())
    }

    fun start() = ratpackServer.start()

    private fun defineApi(): Action<Chain> = Action { chain: Chain ->
        chain
            .get { ctx ->
                WebSockets.websocket(ctx) { ws ->
                    coroutine.launch(Dispatchers.IO) {
                        ws.send(objectMapper.writeValueAsString(
                            ChangeCellsMessage(
                                0,
                                livingCells().map { ChangeSingleCellMessage(ChangeCellAction.BORN, it.x, it.y) }
                            )
                        ))
                        for (event in messagesChannel)
                            ws.send(objectMapper.writeValueAsString(event))
                    }
                }.connect { }
            }
    }

}
