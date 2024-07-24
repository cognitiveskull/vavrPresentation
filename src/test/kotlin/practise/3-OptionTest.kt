package practise

import io.vavr.control.Option
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class `3-OptionTest` {

    @Test
    fun `traditional null checks in java`() {
        var possibleNullObj: Any? = null
        if (possibleNullObj == null) {
            possibleNullObj = "someDefaultValue"
        }
        assertNotNull(possibleNullObj)
    }


    @Test
    fun `None value or some value`() {
        val noneOption: Option<Any> = Option.of(null)
        val someOption: Option<Any> = Option.of("12345")

        val map = noneOption.map {
            it.toString() + "appended string"
        }

        val someMap = someOption.map {
            "$it appended string"
        }

        assertEquals("None", map.toString())
        assertEquals("Some(12345 appended string)", someMap.toString())
    }

    @Test
    fun `default value for non null options`() {
        val name = "baeldung"
        val nameOption = Option.of(name)

        assertEquals("baeldung", nameOption.getOrElse("notbaeldung"))
    }


    @Test
    fun `default value for null options`() {
        val name: String? = null
        val nameOption = Option.of(name)

        assertEquals("baeldung", nameOption.getOrElse("baeldung"))
    }

    @Test
    fun `filtering and mapping optional values`() {
        val numberOption = Option.of(2)
        val actualResult = numberOption
            .map { it + 1 }
            .filter { it % 2 == 0 }

        assertEquals(100, actualResult.getOrElse(100))
    }

    @Test
    fun `flattening iterables in the list`() {
        val numList =  listOf(Option.of(2), Option.of(3), Option.of(null))

//        numberList2.get(2).get()

        val actualResult1 = numList
            .filter {  it.isDefined }
            .map { it.get() }

//        val actualResult2 = numList
//            .flatten()

        assertEquals(listOf(2, 3), actualResult1)
    }

}

