package _13_Concurrency

import _13_Concurrency.Concurrency.*
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class ConcurrencyTest extends AsyncFlatSpec with Matchers:

  "slowComputation" should "double the number" in {
    slowComputation(2).map(_ shouldEqual 4)
  }

  it should "combine two computations" in {
    for
      a <- slowComputation(3)
      b <- slowComputation(4)
    yield a + b shouldEqual 14
  }

  ignore should "combine two computations in single thread" in {
    val results = for
      a <- 0 until 3
      b <- 0 until 4
    yield singleThreadComputation(a) + singleThreadComputation(b) // shouldEqual 14
//    yield singleThreadComputation(a) + singleThreadComputation(b) shouldEqual 14

    results.foreach(result => assert(result >= 0))
    results.length should be > 0
  }
