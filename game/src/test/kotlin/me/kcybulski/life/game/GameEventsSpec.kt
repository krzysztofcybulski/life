package me.kcybulski.life.game

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.coVerify
import io.mockk.mockk
import me.kcybulski.life.game.CellBorn
import me.kcybulski.life.game.CellDie
import me.kcybulski.life.game.GameEvent
import me.kcybulski.life.game.GenerationEnded
import me.kcybulski.life.game.HandlersEventBus
import me.kcybulski.life.game.x

class GameEventsSpec : ShouldSpec({

    val handler = mockk<suspend (GameEvent) -> Unit>(relaxed = true)
    val eventBus = HandlersEventBus(listOf(handler))

    should("emit events") {
        //given
        val start = conway(
            map = map(
                "   ",
                "***",
                "   ",
            ),
            eventBus = eventBus
        )
        //when
        start.nextGeneration()

        //then
        coVerify(exactly = 1) { handler(CellBorn(start.id, 0, 1 x 0)) }
        coVerify(exactly = 1) { handler(CellBorn(start.id, 0, 1 x 2)) }
        coVerify(exactly = 1) { handler(CellDie(start.id, 0, 0 x 1)) }
        coVerify(exactly = 1) { handler(CellDie(start.id, 0, 2 x 1)) }
        coVerify(exactly = 1) { handler(GenerationEnded(start.id, 0)) }
    }

})
