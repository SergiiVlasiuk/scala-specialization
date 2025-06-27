package _01_a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite
import _01_a_taste_of_scala3._06_Methods.AdvancedFeatures.*

import java.io.{ByteArrayOutputStream, PrintStream}

class AdvancedFeaturesSpec extends AnyFunSuite:
  test("inline log simply prints message") {
    log("Hello inline")
  }

  test("given ordering allows sorting") {
    val sorted = List(3, 1, 2).sorted
    assert(sorted == List(1, 2, 3))
  }

  test("lazy val is evaluated only once and value is cached") {
    // Перехоплюємо стандартний вивід
    val outCapture = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(outCapture)) {
      val first = cachedValue
      val second = cachedValue
      assert(first == "Result")
      assert(second == "Result")
    }

    val output = outCapture.toString.linesIterator.toList
    // Перевірка, що println викликалась лише один раз
    val computingLines = output.filter(_.contains("Computing cachedValue"))
    assert(computingLines.length == 1)
  }

  test("call-by-name evaluated each time") {
    var counter = 0
    def inc(): Int = { counter += 1; counter }
    val result = callByName(inc())
    assert(result == 3) // 1 + 2
  }

  test("withResource uses given cleanup") {
    given StringCleanup: Function1[String, Unit] with
      def apply(res: String): Unit = println(s"Cleaning: $res")

    withResource("temp.txt")(using StringCleanup)
  }

  test("withResource uses given cleanup and calls it with correct argument") {
    var cleaned: Option[String] = None

//    import scala3._06_Methods.AdvancedFeatures.given
//    import scala3._06_Methods.AdvancedFeatures.given Function1[String, Unit]

    given StringCleanup: Function1[String, Unit] with
      def apply(res: String): Unit = cleaned = Some(res)

    withResource("temp.txt")

    assert(cleaned.contains("temp.txt"))
  }

  test("withResource prints using message") {
    val stream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(stream)) {
      given StringCleanup: Function1[String, Unit] with
        def apply(res: String): Unit = ()
      withResource("myfile")
    }
    val output = stream.toString
    assert(output.contains("Using: myfile"))
  }
