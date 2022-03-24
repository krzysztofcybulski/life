package me.kcybulski.life

import io.kotest.core.spec.style.ShouldSpec

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
        end.map === map(
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
        end.map === map(
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
        end.map === map(
            "   ",
            "***",
            "   ",
        )
    }

})