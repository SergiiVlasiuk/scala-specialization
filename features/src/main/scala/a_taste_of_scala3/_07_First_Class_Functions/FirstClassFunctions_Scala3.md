# First-Class Functions у Scala 3

---

## Визначення

**Функції першого класу (First-Class Functions)** — це функції, які можна:

- Присвоювати змінним
- Передавати як аргументи іншим функціям
- Повернути як результат із функції
- Зберігати у структурах даних

Фактично функція є звичайним значенням (об’єктом), з яким можна працювати як з будь-яким іншим.

---

## Чому це важливо?

- Scala підтримує функції першого класу, що є основою функціонального програмування.
- Це дозволяє створювати модульний, гнучкий і повторно використовуваний код.
- За допомогою функцій можна будувати абстракції, композиції, обробляти поведінку як дані.

---

## Основні синтаксичні форми

### 1. Оголошення функції як значення

```scala
val addOne: Int => Int = x => x + 1
```

- `Int => Int` — тип функції, яка приймає `Int` і повертає `Int`.
- `x => x + 1` — лямбда-вираз (анонімна функція).

---

### 2. Виклик функції через змінну

```scala
val result = addOne(5)  // result == 6
```

---

### 3. Функції з кількома параметрами

```scala
val sum: (Int, Int) => Int = (a, b) => a + b
```

---

### 4. Передача функції як аргумент

```scala
def applyFunc(f: Int => Int, x: Int): Int = f(x)

val doubled = applyFunc(x => x * 2, 10)  // doubled == 20
```

---

### 5. Повернення функції з функції

```scala
def makeAdder(n: Int): Int => Int = x => x + n

val addFive = makeAdder(5)
val result = addFive(10)  // result == 15
```

---

## Приклад повного коду (production-ready)

```scala
package example

object FirstClassFunctions:

  val addOne: Int => Int = x => x + 1

  val sum: (Int, Int) => Int = (a, b) => a + b

  def applyFunc(f: Int => Int): Int = f(10)

  def makeAdder(n: Int): Int => Int = x => x + n
```

---

## Тести

```scala
package example

import org.scalatest.funsuite.AnyFunSuite

class FirstClassFunctionsTest extends AnyFunSuite:

  test("addOne adds 1 to number") {
    assert(FirstClassFunctions.addOne(4) == 5)
  }

  test("sum adds two numbers") {
    assert(FirstClassFunctions.sum(3, 7) == 10)
  }

  test("applyFunc applies function to 10") {
    val doubleFunc: Int => Int = _ * 2
    assert(FirstClassFunctions.applyFunc(doubleFunc) == 20)
  }

  test("makeAdder creates adder function") {
    val addThree = FirstClassFunctions.makeAdder(3)
    assert(addThree(7) == 10)
  }
```

---

## Пояснення

- Функції як значення (`val addOne`).
- Функції з кількома параметрами (`val sum`).
- Передача функції в якості аргументу (`def applyFunc`).
- Повернення функції з функції (`def makeAdder`).
- Це демонструє базові можливості first-class functions у Scala.

---

## Переваги first-class functions

- Висока гнучкість і розширюваність коду.
- Можливість створення складних абстракцій і композицій.
- Ключова концепція у функціональному програмуванні.

---

# Додаткові теми для подальшого вивчення (за бажанням)

- Каріінг (Currying) та часткове застосування (Partial Application).
- Композиція функцій (`andThen`, `compose`).
- Поліморфні функції.
- Вищі порядки функції (Higher-Order Functions).
- Inline-функції та функції з контекстом (given).

---

# Підсумок

First-Class Functions — це фундаментальна концепція в Scala, що дозволяє розглядати функції як звичайні об'єкти, що відкриває шлях до функціонального стилю програмування, модульності та композиційності.
