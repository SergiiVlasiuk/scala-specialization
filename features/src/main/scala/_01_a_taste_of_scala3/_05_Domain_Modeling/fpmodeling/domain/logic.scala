package _01_a_taste_of_scala3._05_Domain_Modeling.fpmodeling.domain


object CafeLogic:

  def placeOrder(customer: Customer, drink: Drink): Order =
    Order(customer, drink)

  def prepareDrink(order: Order, barista: Barista): PreparedDrink =
    PreparedDrink(order, barista)

  def serveDrink(prepared: PreparedDrink): String =
    val drink = prepared.order.drink
    val customer = prepared.order.customer
    val barista = prepared.by
    s"${barista.name} served ${drink.size} ${drink.drinkType} to ${customer.name}"
