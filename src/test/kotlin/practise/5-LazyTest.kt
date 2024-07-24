package practise

import io.vavr.Lazy
import java.util.UUID
import java.util.function.Supplier
import org.junit.jupiter.api.Test

class `5-LazyTest` {

//    What is supplier
//    There is no requirement that a new or distinct result be
//    returned each time the supplier is invoked

    @Test
    fun `how to use supplier`() {
        val supplier = Supplier<String> {
            println("getting value from supplier")
            UUID.randomUUID().toString()
        }

        println(supplier.get())
        println(supplier.get())
        println(supplier.get())
//
//        val lazySupplier = Lazy.of(supplier)
//
//        println(lazySupplier.isEvaluated)
//
//        println(lazySupplier.get())
//
//        println(lazySupplier.isEvaluated)
//
//        println(lazySupplier.get())
//        println(lazySupplier.get())
    }

//    Deferred Computation:
//    The computation of the value is deferred until it is accessed for the first time.
//
//    Memoization:
//    Once the value is computed, it is cached, so subsequent accesses return the cached value without recomputation.
//
//    Thread-Safety:
//    Vavr's Lazy ensures that the value is computed only once even in concurrent scenarios.

    @Test
    fun `Mapping lazy values`() {
        val supplier = Supplier<Double> {
            val random = Math.random()
            println("getting value from supplier $random")
            random
        }

        val multiplyByHundred = Lazy.of(supplier)
            .map { it * 100 }

        println(multiplyByHundred.isEvaluated)
        println(multiplyByHundred.get())
    }
}

