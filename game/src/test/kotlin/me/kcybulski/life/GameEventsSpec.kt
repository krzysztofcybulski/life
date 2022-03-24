package me.kcybulski.life

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.mockk
import io.mockk.verify
import me.kcybulski.life.game.CellBorn
import me.kcybulski.life.game.CellDie
import me.kcybulski.life.game.EventBus
import me.kcybulski.life.game.GenerationEnded
import me.kcybulski.life.game.x

class GameEventsSpec: ShouldSpec({

    val eventBus = mockk<EventBus>(relaxed = true)

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
        verify(exactly = 1) { eventBus.send(CellBorn(0, 1 x 0))}
        verify(exactly = 1) { eventBus.send(CellBorn(0, 1 x 2))}
        verify(exactly = 1) { eventBus.send(CellDie(0, 0 x 1))}
        verify(exactly = 1) { eventBus.send(CellDie(0, 2 x 1))}
        verify(exactly = 1) { eventBus.send(GenerationEnded(0))}
    }

})