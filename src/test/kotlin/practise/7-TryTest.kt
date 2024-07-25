package practise

import io.vavr.control.Try
import org.junit.jupiter.api.Test

class `7-TryTest` {

    @Test
    fun `try methods`() {
        // Create Try instances
        val success = Try.of { 10 }
        val failure = Try.of { 1 / 0 }


//        // Transformations
//        val mappedSuccess = success.map { x: Int -> x * 2 } // Success(20)
//        val flatMappedSuccess = success.flatMap { x: Int ->
//            Try.of { x + 5 }
//        } // Success(15)
//
//        println("Mapped Success: $mappedSuccess")
//        println("FlatMapped Success: $flatMappedSuccess")


//        // Folding
//        val foldSuccess = success.fold(
//            { throwable: Throwable -> "Failed: " + throwable.message },
//            { value: Int -> "Success: $value" }
//        ) // Success: 10
//
//        val foldFailure = failure.fold(
//            { throwable: Throwable -> "Failed: " + throwable.message },
//            { value: Int -> "Success: $value" }
//        ) // Failed: / by zero
//
//        println("Fold Success: $foldSuccess")
//        println("Fold Failure: $foldFailure")

//        // Recovery
//        val recovered = failure.recover { _: Throwable? -> 0 } // Success(0)
//        val recoveredWith = failure.recoverWith { _: Throwable? ->
//            Try.of { 0 }
//        } // Success(0)
//
//        println("Recovered: $recovered")
//        println("Recovered With: $recoveredWith")
//
        // Get values
        val successValue = success.getOrElse(0) // 10
        val failureValue = failure.getOrElse(0) // 0

        println("Success Value: $successValue")
        println("Failure Value: $failureValue")
    }
}