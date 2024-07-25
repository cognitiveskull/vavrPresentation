package practise

import io.vavr.Function1
import io.vavr.Function2
import io.vavr.Function3
import io.vavr.Function4
import org.junit.jupiter.api.Test

class FunctionsTest {

    // Set of functional interfaces called
    // Function1, Function2, Function3, ..., up to Function8

    @Test
    fun `functions theory`() {
        //single argument fn
        val square = Function1 { a: Int -> a * a }
        printWithSpace("single arg fn: ${square.apply(4)}")

        //2 argument fn
        val add = Function2 { a: Int, b: Int -> a + b }
        printWithSpace("two arg fn: ${add.apply(2, 3)}")

        //3 argument fn
        val multiplyThree = Function3<Int, Int, Int, Int> { a: Int, b: Int, c: Int -> a * b * c }
        printWithSpace("three arg fn: ${multiplyThree.apply(2, 3, 4)}") // Output: 24

        //4 argument fn
        val addFour = Function4<Int, Int, Int, Int, Int> { a: Int, b: Int, c: Int, d: Int -> a + b + c + d }
        printWithSpace("four arg fn: ${addFour.apply(1, 2, 3, 4)}") // Output: 10

        // .... upto 8 arguments


        //apply one argument at a time
        printWithSpace(add.apply(2).apply(2))
    }

    // what can we do with vavr functions ?

    @Test
    fun `function composition`() {
        val increment = Function1<Int, Int> { value -> value + 1 }
        val square = Function1<Int, Int> { value -> value * value }
        val sum: Function2<Int, Int, Int> = Function2 { a: Int, b: Int -> a + b }

        val squareOfSum = sum.andThen(square).andThen(increment)

        printWithSpace(
            squareOfSum
                .apply(2, 3)
        )
    }

    @Test
    fun `function currying`() {
        val add = Function2 { a: Int, b: Int -> a + b }

        val addTwo = add.apply(2)

        printWithSpace(addTwo.apply(3)) // Output: 5
        printWithSpace(addTwo.apply(5)) // Output: 5
        printWithSpace(addTwo.apply(7)) // Output: 5
    }

// Higher-Order Functions
//
//    Functions as Parameters: You can pass functions as arguments to other functions.
//    Functions as Return Types: Functions can return other functions.
//    Lambda Expressions: Often used to define the functions that are passed as parameters.

//    val add: (Int, Int) -> Int = { a, b -> a + b }
//    println(add(2, 3))  // Output: 5

//    Currying
//    fun add(a: Int): (Int) -> Int = { b -> a + b }
//    println(add(2)(3))  // Output: 5
}

fun printWithSpace(string: Any) {
    println("-------------------------------------------------")
    println(string)
    println("-------------------------------------------------\n")

}