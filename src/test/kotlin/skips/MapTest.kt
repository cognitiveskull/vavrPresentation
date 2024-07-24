package skips

import io.vavr.control.Either
import io.vavr.control.Either.left
import io.vavr.control.Either.right
import io.vavr.control.Option
import io.vavr.control.Try
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

data class CustomError(val statusCode: Int) : Throwable()
class MapTest {

    private fun getEitherElement(element: Int): Either<CustomError, Int> {
        return if (element > 0) {
            right(element)
        } else {
            left(CustomError(500))
        }
    }

    @Test
    fun `map on single elements success`() {
        // mapping Either right/left value to plain primitive value // non monad value
        val add200: Int = getEitherElement(200).map { element ->
            element + 200
        }.getOrElse(200)

        assertEquals(add200, 400)
    }

    @Test
    fun `map on single elements failure`() {
        // mapping Either right/left value to plain primitive value // non monad value
        val add200: Int = getEitherElement(-1).map { element ->
            element + 200
        }.getOrElse(200)

        assertEquals(add200, 200)
    }


    @Test
    fun `map on single elements success with wrapped success value`() {
        // mapping Either right/left value to wrapped primitive value
        val add200 = getEitherElement(200).map { element ->
            right<CustomError, Int>(element + 200)
        }.getOrElse {
            left(CustomError(500))
        }

        assertTrue(add200.isRight)
        assertEquals(add200.get(), 400)
    }

    @Test
    fun `map on single element failure with wrapped error value`() {
        val add200: Either<CustomError, Int> = getEitherElement(-1).map { element ->
            right<CustomError, Int>(element + 200)
        }.getOrElse {
            left(CustomError(500))
        }

        assertTrue(add200.isLeft)
        assertEquals(add200.left, CustomError(500))
    }

    @Test
    fun `map on single element failure with wrapped success value`() {
        val add200: Either<CustomError, Int> = getEitherElement(-1).map { element ->
            right<CustomError, Int>(element + 200)
        }.getOrElse {
            right(200)
        }

        assertTrue(add200.isRight)
        assertEquals(add200.get(), 200)
    }

    @Test
    fun `map on single element failure with mapLeft with wrapped error value`() {
        val add200 = getEitherElement(-1).map { element ->
            element + 200
        }.mapLeft {
            CustomError(500)
        }

        assertTrue(add200.isLeft)
        assertEquals(add200.left, CustomError(500))
    }

    @Test
    fun `map on single element failure with orElse with wrapped error value`() {
        val add200 = getEitherElement(-1).map { element ->
            element + 200
        }.orElse {
            left(CustomError(500))
        }

        assertTrue(add200.isLeft)
        assertEquals(add200.left, CustomError(500))
    }

    @Test
    fun `map on single element failure with orElse with wrapped success value`() {
        val add200 = getEitherElement(-1).map { element ->
            element + 200
        }.orElse {
            right<CustomError, Int>(200)
        }

        assertTrue(add200.isRight)
        assertEquals(add200.get(), 200)
    }


    @Test
    fun `map on single element failure with getOrElseGet with primitive success value`() {
        val add200: Int = getEitherElement(200).map { element ->
            element + 200
        }.getOrElseGet {
            200 // default
        }

        assertEquals(add200, 400)
    }

    @Test
    fun `map on single element failure with getOrElseGet with wrapped failure value`() {
        val add200 = getEitherElement(-1).map { element ->
            right<CustomError, Int>(element + 200)
        }.getOrElseGet {
            left(CustomError(500))
        }

        assertTrue(add200.isLeft)
        assertEquals(add200.left, CustomError(500))
    }

    @Test
    fun `map on single element failure with getOrElseThrow with wrapped failure value`() {
        val add200: Try<Either<CustomError, Int>> = Try.of {
            getEitherElement(-1).map { element ->
                right<CustomError, Int>(element + 200)
            }.getOrElseThrow { e: CustomError ->
                CustomError(500)
                // directly throws the exception which again needs to be handled using try catch
            }
        }

        assertTrue(add200.isFailure)
        assertEquals(add200.toEither().left, CustomError(500))
    }

//    map vs flatmap

//    map
//    Purpose: Transforms the value inside the container.
//    Behavior: Applies a function to the value if it is present and returns a new container with the transformed value. If the container is empty, it returns an empty container.
//    Usage: Use map when the function you are applying returns a plain value.

//    flatMap
//    Purpose: Transforms the value inside the container and flattens the result.
//    Behavior: Applies a function to the value if it is present and returns the result directly if it is another container, effectively "flattening" nested containers. If the container is empty, it returns an empty container.
//    Usage: Use flatMap when the function you are applying returns another container.
//
    @Test
    fun `map vs flatmap`() {

        val optionValue: Option<Int> = Option.of(2)

        // Using map to transform the value
        val mappedOption: Option<Int> = optionValue.map { it * 2 }

        println(mappedOption)  // Output: Some(4)

        // Using flatMap to transform the value and flatten the result

        val flatMappedOption: Option<Int> = optionValue.flatMap {
            if (it % 2 == 0) Option.of(it * 2) else Option.none()
        }

        println(flatMappedOption)
    }
}

