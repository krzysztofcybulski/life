package me.kcybulski.life.server

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.kcybulski.life.coordinator.GamesCommandHandler
import me.kcybulski.life.coordinator.InMemoryGamesRepository
import me.kcybulski.life.coordinator.NextGeneration
import me.kcybulski.life.game.GameFactory
import me.kcybulski.life.game.HandlersEventBus
import me.kcybulski.life.game.x

fun main() = runBlocking {

    val messagesChannel = Channel<WebMessage>(capacity = 1000)
    val batchCellsChangeCellsHandler = BatchCellsChangeHandler(messagesChannel)

    val eventBus = HandlersEventBus(
        listOf(batchCellsChangeCellsHandler::onEvent)
    )
    val factory = GameFactory(eventBus)
    val repository = InMemoryGamesRepository()

    val game = factory.conway(
        setOf(
            0 x 0,
            1 x 0,
            2 x 0,
            4 x 0,
            0 x 1,
            3 x 2,
            4 x 2,
            1 x 3,
            2 x 3,
            4 x 3,
            0 x 4,
            2 x 4,
            4 x 4,
        )
    )
    repository.save(game)

    val handler = GamesCommandHandler(repository)

    launch {
        while (true) {
            delay(50)
            handler.handle(NextGeneration(game.id))
        }
    }

    Server(messagesChannel, { repository.find(game.id)?.livingCells ?: emptySet() }, this).start()

}
