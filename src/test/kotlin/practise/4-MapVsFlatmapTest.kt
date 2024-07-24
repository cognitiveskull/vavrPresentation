package practise

import io.vavr.control.Option
import org.junit.jupiter.api.Test


class `4-MapVsFlatmapTest` {

//    Option
//    Option in Vavr is a container type that may or may not contain a value.
//    It is used to avoid null values and the potential for NullPointerException

//    map on Option
//    Purpose: Applies a function to the value inside the Option if it is present,
//    returning a new Option with the transformed value.
//    Usage: Use map when you have a function that transforms the value inside the Option.

    @Test
    fun `Basic option with map`() {
        val someValue: Option<Int> = Option.of(5)
        val mappedValue: Option<Int> = someValue.map { x -> x * 2 }

        println("Mapped value: $mappedValue")
    }

//    flatMap on Option
//    Purpose: Applies a function that returns an Option to the value inside the Option,
//    flattening the result to avoid nested Options.
//    Usage: Use flatMap when your function returns an Option.
    @Test
    fun `Basic option with flatmap`() {
        val someValue = Option.of(5)
        val flatMappedValue = someValue.flatMap { x: Int -> Option.of(x * 2) }

        println("FlatMapped value: $flatMappedValue")
    }

//    List
//    List in Vavr is an immutable, functional data structure
//    that represents a sequence of elements.

    @Test
    fun `Basic list with map`() {
        val list = listOf(1, 2, 3)
        val mappedList = list.map { x -> x * 2 }

        println("Mapped list: $mappedList")
    }

//    flatMap on List
//    Purpose: Applies a function that returns
//    a list to each element of the original list
//    and flattens the result into a single list.

//    Usage: Use flatMap when your function returns a list,
//    and you want to concatenate the results into a single list.
    @Test
    fun `Basic list with flatmap`() {
        val list = listOf(1, 2, 3)
        val mappedList = list.flatMap { x -> listOf(x, x * 2) }

        println("FlatMapped list: $mappedList")
    }

}