package a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain

import a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain.{Customer, Barista, Drink, DrinkType, DrinkSize}

import org.scalatest.funsuite.AnyFunSuite
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OrderSpec extends AnyFunSuite {

  test("PreparedDrink.serve should print correct message") {
    val out = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      val customer = Customer("Serhii")
      val barista = Barista("Oksana")
      val drink = Drink(DrinkType.Cappuccino, DrinkSize.Large)
      val order = Order(customer, drink)
      val prepared = PreparedDrink(customer, drink, barista)

      prepared.serve()
    }

    val output = out.toString.trim
    assert(output == "Oksana served Large Cappuccino to Serhii")
  }
}