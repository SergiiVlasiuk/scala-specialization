# Functional Error Handling in Scala 3

## A quick review

- Functional programmers don’t use `null` values 
- A main replacement for `null` values is to use the `Option` classes 
- Functional methods don’t throw exceptions; instead they return values like `Option`, `Try`, or `Either` 
- Common ways to work with `Option` values are `match` and `for` expressions 
- Options can be thought of as containers of one item (`Some`) and no items (`None`)
- Options can also be used for optional constructor or method parameters

## A First Example

```scala
def makeInt(s: String): Option[Int] =
  try Some(s.toInt)
  catch case _: NumberFormatException => None
```

This function tries to convert a string to an integer. If it fails, it returns `None` instead of throwing an exception.

## Using Option/Some/None

```scala
val a = makeInt("42")       // Some(42)
val b = makeInt("not a num") // None
```

The result is wrapped in an `Option`, which can be either `Some(value)` or `None`.

## Being a Consumer of makeInt

### Using Match Expression

```scala
makeInt("123") match
  case Some(i) => println(s"Parsed: $i")
  case None    => println("Invalid number")
```

### Using For Expression

```scala
val result = for
  a <- makeInt("1")
  b <- makeInt("2")
  c <- makeInt("3")
yield a + b + c

println(result) // Some(6)
```

If any of the steps returns `None`, the result will also be `None`.

## Thinking of Option as a Container

```scala
val result = makeInt("10").map(_ * 2) // Some(20)
```

You can use `map`, `flatMap`, `getOrElse`, etc., just like with collections.

## Using Option to Replace null

### Traditional (Java-style):

```scala
val name: String = user.getName // Might be null
```

### Functional style:

```scala
val name: Option[String] = Option(user.getName)
```

This makes `null` handling explicit.

## Option Isn’t the Only Solution

### Either

```scala
def safeDivide(a: Int, b: Int): Either[String, Int] =
  if b == 0 then Left("Division by zero") else Right(a / b)

val result = safeDivide(10, 2).map(_ * 2) // Right(10)

result match
  case Right(value) => println(s"Success: $value")
  case Left(error)  => println(s"Error: $error")

val folded = result.fold(
  err => s"Failed: $err",
  value => s"Got: $value"
)

println(folded)
```

### Try

```scala
import scala.util.{Try, Success, Failure}

def risky(s: String): Try[Int] = Try(s.toInt)

risky("123") match
  case Success(v) => println(s"Parsed: $v")
  case Failure(e) => println(s"Error: ${e.getMessage}")

val value = risky("10").getOrElse(0)
```

### Validated (cats)

```scala
import cats.data.Validated
import cats.data.Validated.{Valid, Invalid}

val v1: Validated[String, Int] = Valid(1)
val v2: Validated[String, Int] = Invalid("Missing input")

val result = (v1, v2).mapN(_ + _) // requires cats.syntax.apply._

result match
  case Valid(v)   => println(s"Valid: $v")
  case Invalid(e) => println(s"Invalid: $e")
```

### Other Useful Methods

```scala
val opt = makeInt("12")
  .filter(_ > 10)
  .orElse(Some(0)) // fallback
```

---

# Tests

```scala
import scala.util.*
import org.scalatest.funsuite.AnyFunSuite

class FunctionalErrorHandlingTest extends AnyFunSuite:

  test("makeInt returns Some for valid input"):
    assert(makeInt("123") == Some(123))

  test("makeInt returns None for invalid input"):
    assert(makeInt("abc").isEmpty)

  test("Option with for-comprehension"):
    val sum = for
      a <- makeInt("1")
      b <- makeInt("2")
    yield a + b
    assert(sum == Some(3))

  test("Option short-circuits in for-comprehension"):
    val sum = for
      a <- makeInt("1")
      b <- makeInt("oops")
    yield a + b
    assert(sum == None)

  test("Either success and failure"):
    assert(safeDivide(4, 2) == Right(2))
    assert(safeDivide(4, 0) == Left("Division by zero"))

  test("Try success and failure"):
    assert(risky("100") == Success(100))
    assert(risky("bad").isFailure)
```

---

# Interview Questions & Answers

### Q1: What is `Option` in Scala?

**A:** A container type that represents the presence (`Some`) or absence (`None`) of a value.

### Q2: How does `Option` help avoid `null`?

**A:** It replaces the need for `null` by making the possibility of missing data explicit.

### Q3: When would you prefer `Either` over `Option`?

**A:** When you want to include information about the error (`Left`), not just indicate failure.

### Q4: What’s the difference between `Option` and `Try`?

**A:** `Option` is for success/failure without error details, `Try` captures exceptions and holds either `Success` or `Failure`.

### Q5: Why use for-comprehensions with `Option`?

**A:** They allow chaining multiple `Option` operations cleanly, automatically handling `None` propagation.

### Q6: How does `map` differ from `flatMap` in `Option`?

**A:** `map` transforms the value, `flatMap` flattens nested `Option`s.

### Q7: When would you use `Validated`?

**A:** When you need to accumulate multiple validation errors rather than fail fast as with `Either`.

---

# Summary

- `Option` is a powerful way to handle potential absence of values.
- `for` comprehensions and methods like `map`, `flatMap`, `filter`, `getOrElse`, and `orElse` provide fluent and safe error handling.
- When error details are needed, use `Either`, `Try`, or `Validated` instead of `Option`.
- Prefer functional error handling over throwing exceptions in pure functional code.
- Use `Validated` for accumulating errors, especially in form validations or configuration checks.

