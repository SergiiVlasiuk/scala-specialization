package _01_a_taste_of_scala3._05_Domain_Modeling.oopmodeling.logic

import _01_a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain.*
import org.scalatest.funsuite.AnyFunSuite
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CafeAppSpec extends AnyFunSuite {

  test("Full scenario from customer to served drink") {
    val out = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      val customer = Customer("Serhii")
      val barista = Barista("Oksana")

      val drink = Drink(DrinkType.Latte, DrinkSize.Medium)
      val order = customer.placeOrder(drink)
      val prepared = barista.prepareOrder(order)
      prepared.serve()
    }

    val output = out.toString.trim
    assert(output == "Oksana served Medium Latte to Serhii")
  }
}
