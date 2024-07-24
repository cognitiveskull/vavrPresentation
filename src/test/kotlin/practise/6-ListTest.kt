package practise

import io.vavr.collection.List
import org.junit.jupiter.api.Test


class `6-ListTest` {

    @Test
    fun `Immutability`() {
        val list = List.of(1, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val droppedList = list.drop(5)

        println("original list: $list")
        println("droppedList list: $droppedList")
    }

    @Test
    fun `Converting Between Vavr and Java Lists`() {
        val vavrList = List.of(1, 2, 3, 4, 5)

        // Converting to a Java List
        val javaList = vavrList.asJava()
        println("Java List: $javaList")

        // Converting from a Java List
        val anotherJavaList: kotlin.collections.List<Int> = mutableListOf(6, 7, 8, 9, 10)
        val anotherVavrList = List.ofAll(anotherJavaList)
        println("Vavr List from Java List: $anotherVavrList")
    }

    @Test
    fun `Functional Operations`() {
        val list = List.of(1, 2, 3, 4, 5)

        val squaredList: List<Int> = list.map { i -> i * i }
        println("Squared List: $squaredList")

        // Filtering elements
        val evenList: List<Int> = list.filter { i -> i % 2 == 0 }
        println("Even elements: $evenList")


        // Folding (reducing) elements
        val sum: Int = list.fold(0) { acc, i -> acc + i }
        println("Sum of elements: $sum")
    }



    @Test
    fun `List operations`() {
        val list = List.of(1, 1, 2, 3, 4, 5, 6, 7, 8, 9)

//        println(list.last())
//        println(list.combinations(2))
//        println(list.average())
//        println(list.drop(2)) //drops from left
//        println(list.dropRight(2)) //drops from right
//        println(list.dropUntil { it > 5}) //drops from left
//        println(list.existsUnique { it == 5 })
//        println(list.existsUnique { it == 1 })
//        println(list.indexWhereOption { it == 5 })
//        println(list.zipWithIndex())
    }


    @Test
    fun `kotlin list`() {
        // Creating a list
        val list = listOf(1, 2, 3, 4, 5)

        // Accessing elements
        println("First element: ${list.first()}")
        println("Rest of the elements: ${list.drop(1)}")

        // Mapping elements
        val squaredList = list.map { it * it }
        println("Squared List: $squaredList")

        // Filtering elements
        val evenList = list.filter { it % 2 == 0 }
        println("Even elements: $evenList")

        // Folding (reducing) elements
        val sum = list.fold(0) { acc, i -> acc + i }
        println("Sum of elements: $sum")

    }

//    If primarily working in a Java environment
//    and need robust functional programming capabilities
//    and immutable collections

//    When you need seamless integration with
//    other Vavr constructs like Option, Either, and Try.

}