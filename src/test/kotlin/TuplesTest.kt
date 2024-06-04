
import io.vavr.Tuple
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.assertEquals


class TuplesTest {

    fun whenCreatesTuple_thenCorrect1() {
        val java8 = Tuple.of("Java", 8)
        val element1 = java8._1
        val element2 = java8._2()

        assertEquals("Java", element1)
        assertEquals(8, element2)
    }

    @Test
    fun whenCreatesTuple_thenCorrect2() {
        val java8 = Tuple.of("Java", 8, 1.8)

        val element1 = java8._1
        val element2 = java8._2()
        val element3 = java8._3()

        assertEquals("Java", element1)
        assertEquals(8, element2)
        assertEquals(1.8, element3, 0.1)

//        val java9 = Tuple.of("Java", 8, 1.8,1,2,3,4,5) //upto 8 values
    }
}