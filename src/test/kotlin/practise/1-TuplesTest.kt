package practise

import io.vavr.Tuple
import io.vavr.Tuple3
import io.vavr.Tuple8
import kotlin.test.Test


class `1-TuplesTest` {

    @Test
    fun `Basic tuple creation`() {
        val java8 = Tuple.of("Java", 8)
        val element1 = java8._1
        val element2 = java8._2()

        println(element1)
        println(element2)
    }

    @Test
    fun `Tuples upto 8 arguments`() {
        val java8: Tuple3<String, Int, Double> = Tuple.of("Java", 8, 1.8)

        val element1 = java8._1
        val element2 = java8._2
        val element3 = java8._3()

        println(element1)
        println(element2)
        println(element3)

        val java9: Tuple8<String, Int, Double, Int, Int, Int, Int, Int> = Tuple.of("Java", 8, 1.8,1,2,3,4,5) //upto 8 values
    }

//    Kotlin alternatives :

    // Using Pair
    val pair = Pair(1, "one")

    // Using Triple
    val triple = Triple(1, "one", 1.0)
}