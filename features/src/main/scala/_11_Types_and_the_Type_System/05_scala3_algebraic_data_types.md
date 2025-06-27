# 📘 Scala 3 — Algebraic Data Types (ADT)

---

## 1. Enumerations

### Що це?

Перелічувані типи (enum) — це набір констант, які позначають обмежений набір значень.

### Приклад:

```scala
enum Color:
  case Red, Green, Blue

val c: Color = Color.Red

c match
  case Color.Red => println("Red")
  case Color.Green => println("Green")
  case Color.Blue => println("Blue")
```

---

## 2. Algebraic Data Types (ADT)

### Що це?

ADT — це складні типи, що складаються з інших типів через **композицію**. Основні види:

- **Sum types (сумарні типи)** — "або": об’єкт може бути одним із варіантів (як enum або sealed trait з case classes).
- **Product types (добуткові типи)** — "і": об’єкт містить кілька значень одночасно (як case class).

### Приклад:

```scala
sealed trait Shape

case class Circle(radius: Double) extends Shape
case class Rectangle(width: Double, height: Double) extends Shape

def area(shape: Shape): Double = shape match
  case Circle(r) => math.Pi * r * r
  case Rectangle(w, h) => w * h
```

---

## 3. Generalized Algebraic Data Types (GADTs)

### Що це?

GADT — це розширення ADT, що дозволяє кожному варіанту мати свій тип, який компілятор розуміє при pattern matching.

### Приклад Scala 3 (GADT з parametric types):

```scala
sealed trait Expr[T]

case class IntValue(value: Int) extends Expr[Int]
case class BoolValue(value: Boolean) extends Expr[Boolean]
case class Add(lhs: Expr[Int], rhs: Expr[Int]) extends Expr[Int]
case class Equals(lhs: Expr[Int], rhs: Expr[Int]) extends Expr[Boolean]

def eval[T](expr: Expr[T]): T = expr match
  case IntValue(v) => v
  case BoolValue(b) => b
  case Add(l, r) => eval(l) + eval(r)
  case Equals(l, r) => eval(l) == eval(r)
```

---

## 4. Desugaring Enumerations

### Що це?

У Scala 3 `enum` фактично "цукрова" надбудова над sealed traits і case objects/classes.

Наприклад, enum:

```scala
enum Direction:
  case North, South, East, West
```

Компілірується як:

```scala
sealed trait Direction
case object North extends Direction
case object South extends Direction
case object East extends Direction
case object West extends Direction
```

---

## 🧪 Тести (ScalaTest)

```scala
import org.scalatest.funsuite.AnyFunSuite

class ADTTests extends AnyFunSuite:

  test("Area of Circle") {
    val c = Circle(1.0)
    assert(area(c) == math.Pi)
  }

  test("Area of Rectangle") {
    val r = Rectangle(2.0, 3.0)
    assert(area(r) == 6.0)
  }

  test("Evaluate Expr[Int]") {
    val expr = Add(IntValue(3), IntValue(4))
    assert(eval(expr) == 7)
  }

  test("Evaluate Expr[Boolean]") {
    val expr = Equals(IntValue(3), IntValue(3))
    assert(eval(expr))
  }
```

---

## 🧠 Питання для співбесіди

**1. Що таке Algebraic Data Types?**  
> Це типи даних, побудовані з простих типів за допомогою "і" (product) та "або" (sum) комбінацій.

---

**2. Чим sum type відрізняється від product type?**  
> Sum type — це вибір між кількома варіантами (як enum або sealed trait), product type — це об'єкт із кількома полями одночасно (case class).

---

**3. Що таке GADT?**  
> Розширення ADT, де тип варіанта залежить від параметру типу, що дозволяє точніше типізувати патерн матчинг.

---

**4. Як Scala 3 реалізує enum під капотом?**  
> Через sealed traits та case objects/classes — "цукрова" надбудова для зручності.

---

## 📌 Підсумок

- ADT — потужний інструмент для моделювання складних структур даних  
- GADT дозволяє гнучко і типобезпечно працювати з виразами різних типів  
- enum — це зручний спосіб описати обмежені множини значень