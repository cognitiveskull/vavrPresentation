import io.vavr.control.Option
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class OptionTest {
    @Test
    fun `null checks without options`() {
        var possibleNullObj: Any? = null
        if (possibleNullObj == null) {
            possibleNullObj = "someDefaultValue"
        }
        assertNotNull(possibleNullObj)
    }


    @Test // has either None or Some value
    fun givenValue_whenCreatesOption_thenCorrect() {
        val noneOption: Option<Any> = Option.of(null)
        val someOption: Option<Any> = Option.of("12345")

        val map = noneOption.map {
            it.toString() + "appended string"
        }

        assertEquals("None", map.toString())
        assertEquals("Some(12345)", someOption.toString())
    }

    @Test // default value assignment in case of null
    fun givenNull_whenCreatesOption_thenCorrect() {
        val name: String? = null
        val nameOption = Option.of(name)

        assertEquals("baeldung", nameOption.getOrElse("baeldung"))
    }


    @Test //default value is being returned for non null option
    fun givenNonNull_whenCreatesOption_thenCorrect() {
        val name = "baeldung"
        val nameOption = Option.of(name)

        assertEquals("baeldung", nameOption.getOrElse("notbaeldung"))
    }

}

