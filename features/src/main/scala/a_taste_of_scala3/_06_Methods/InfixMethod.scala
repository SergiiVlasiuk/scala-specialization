package a_taste_of_scala3._06_Methods

object InfixMethod:
  extension (s: String)
    def likes(drink: String): Boolean =
      drink == "coffee"
