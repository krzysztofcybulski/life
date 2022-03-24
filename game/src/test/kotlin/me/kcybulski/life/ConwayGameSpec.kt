package me.kcybulski.life

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ConwayGameSpec: ShouldSpec({

    should("kill lonely cell") {
        //given
        val start = conway(
            map = map(
                "   ",
                " * ",
                "   ",
            )
        )
        //when
        val end = start.nextGeneration()

        //then
        end.livingCells shouldBe map(
            "   ",
            "   ",
            "   "
        )
    }

    should("keep cells with friends") {
        //given
        val start = conway(
            map = map(
                "    ",
                " ** ",
                " ** ",
                "    ",
            )
        )
        //when
        val end = start.nextGeneration()

        //then
        end.livingCells shouldBe map(
            "    ",
            " ** ",
            " ** ",
            "    "
        )
    }

    should("born new cell with 3 friends") {
        //given
        val start = conway(
            map = map(
                " * ",
                " * ",
                " * ",
            )
        )
        //when
        val end = start.nextGeneration()

        //then
        end.livingCells shouldBe map(
            "   ",
            "***",
            "   ",
        )
    }

})