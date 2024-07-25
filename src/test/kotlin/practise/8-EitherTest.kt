package practise

import io.vavr.control.Either
import io.vavr.control.Either.left
import io.vavr.control.Either.right
import io.vavr.control.Option
import java.util.function.Function
import org.junit.jupiter.api.Test


class `8-EitherTest` {

//    Two Possible Values:
//
//    Left<L>: Typically represents a failure or error, containing a value of type L.
//    Right<R>: Represents a success, containing a value of type R.

//    Immutability:
//
//    Once created, the Either instance cannot be modified.

//    Functional Operations:
//    Provides operations like map, flatMap, fold, etc., to work with the values in a functional manner.

    @Test
    fun `Basic either`() {
        val right = right<String, Int>(10)
        val left = left<String, Int>("Error occurred")

        println("Right: $right")
        println("Left: $left")
    }

//    Purpose: Applies a function to the value in a Right, leaving a Left unchanged.
//    Usage: Use map when you have a function that transforms the Right value.

    @Test
    fun `Either with map`() {
        val right: Either<String, Int> = right(10)
        val mappedRight = right.map { x: Int -> x * 2 }

        println("Mapped Right: $mappedRight") // Output: Right(20)

        val left: Either<String, Int> = left("Error occurred")
        val mappedLeft = left.map { x: Int -> x * 2 }

        println("Mapped Left: $mappedLeft") // Output: Left(Error occurred)
    }

//    Purpose: Applies a function that returns an Either to the value in a Right, flattening the result.
//    Leaves a Left unchanged.
//    Usage: Use flatMap when your function returns an Either.
    @Test
    fun `Either with flatmap`() {
        val right: Either<String, Int> = right(10)
        val flatMappedRight = right.flatMap { x: Int -> right(x * 2) }

        println("FlatMapped Right: $flatMappedRight") // Output: Right(20)

        val left: Either<String, Int> = left("Error occurred")
        val flatMappedLeft = left.flatMap { x: Int -> right(x * 2) }

        println("FlatMapped Left: $flatMappedLeft") // Output: Left(Error occurred)
    }

//    Purpose: Applies one of two functions depending on whether the Either is a Left or Right.
//    Usage: Use fold to handle both possible cases of an Either.

    @Test
    fun fold() {
        val right: Either<String, Int> = right(10)
        val resultRight = right.fold(
            { left: String -> "Error: $left" },
            { rightVal: Int -> "Success: $rightVal" }
        )

        println(resultRight) // Output: Success: 10

        val left: Either<String, Int> = left("Error occurred")
        val resultLeft = left.fold(
            { leftVal: String -> "Left Error: $leftVal" },
            { rightVal: Int -> "Right Success: $rightVal" }
        )

        println(resultLeft) // Output: Error: Error occurred
    }

    private fun divide(a: Int, b: Int): Either<String, Int> {
        if (b == 0) {
            return left("Cannot divide by zero")
        }
        return right(a / b)
    }

    private fun square(x: Int): Either<String, Int> = right(x * x)

    @Test
    fun `Error Handling with Either`() {
        val result: Either<String, Int> = divide(10, 0)
            .flatMap { x -> square(x) }

        println(result) // Output: Left(Cannot divide by zero)
    }


    // Either methods


//    Vavr's Either class offers a variety of methods that enable functional operations on the contained values.
//    These methods allow you to transform, inspect, and handle the values in both Left and Right

    @Test
    fun `creation methods`() {
        val left: Either<String, Int> = left("Error")
        val right: Either<String, Int> = right(10)
    }

    @Test
    fun `Inspection Methods`() {
        val left: Either<String, Int> = left("Error")
        val right: Either<String, Int> = right(10)

        val isLeft: Boolean = left.isLeft // true
        val isRight: Boolean = right.isRight //true
    }

    @Test
    fun `Transformation Methods`() {
        val left: Either<String, Int> = left("Error")
        val right: Either<String, Int> = right(10)

        // Transforms the Right value if present, leaving Left unchanged.
        val mappedRight: Either<String, Int> = right.map { x -> x * 2 } // Right(20)

        // Transforms the Left value if present, leaving Right unchanged.
        val mappedLeft: Either<String, Int> = left.mapLeft { err -> err.uppercase() } // Left(ERROR)


        // Transforms the Right value if present using a function that returns an Either, flattening the result.
        val flatMappedRight: Either<String, Int> = right.flatMap(Function { x: Int ->
            right(x * 2)
        }) // Right(20)

    }

    @Test
    fun `Folding and Handling Methods`() {
        val left: Either<String, Int> = left("Error")
        val right: Either<String, Int> = right(10)

        // Applies one of two functions to the value, depending on whether it is Left or Right.

        val resultRight: String = right.fold(
            { err -> "Error: $err" },
            { `val` -> "Success: $`val`" }
        ) // Success: 10

        val resultLeft: String = left.fold(
            { err -> "Error: $err" },
            { `val` -> "Success: $`val`" }
        ) // Error: Error
    }

    @Test
    fun `Recovery Methods`() {
        val left: Either<String, Int> = left("Error")
        val right: Either<String, Int> = right(10)

        val valueRight: Either<String, Int> = right.orElse { right(0) } // 10
        val valueLeft: Either<String, Int> = left.orElse {
            right(0)
//            left("error value")
        } // 0

        val valueRightSupplier: Int = right.getOrElseGet { 0 } // 10
        val valueLeftSupplier: Int = left.getOrElseGet { 0 } // 0

        val valueRightThrow: Int = right.getOrElseThrow { _ -> RuntimeException("Error") } // 10
        val valueLeftThrow: Int = left.getOrElseThrow { _ -> RuntimeException("Error") } // Throws RuntimeException
    }

    @Test
    fun `Conversion Methods`() {
        val left: Either<String, Int> = left("Error")
        val right: Either<String, Int> = right(10)

//        toOptional(): Converts the Right value to an Optional, or returns an empty Optional if Left.

        val optionalRight: Option<Int>? = right.toOption() // Optional[10]
        val optionalLeft: Option<Int>? = left.toOption() // Optional.empty

//        swap(): Swaps the Left and Right values, transforming an Either<L, R> into an Either<R, L>.

        val swappedRight: Either<Int, String> = right.swap() // Left(10)
        val swappedLeft: Either<Int, String> = left.swap() // Right(Error)
    }


}