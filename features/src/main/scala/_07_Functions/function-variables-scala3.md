# Function Variables у Scala 3 (розширено)

## 🔹 Що таке function variable?

Function variable — це змінна, яка зберігає функцію. Функції в Scala — це значення першого класу.

```scala
val square = (x: Int) => x * x
square(4) // 16
```

---

## 🔹 Виклик функції

```scala
val add = (x: Int, y: Int) => x + y
add(2, 3) // 5
```

---

## 🔹 Function vs Method (def vs val)

| Критерій                   | `def` (метод)                            | `val` (функція)                             |
|----------------------------|------------------------------------------|---------------------------------------------|
| Що це                      | Метод                                    | Значення типу `FunctionX`                   |
| Коли створюється контекст  | Під час кожного виклику                  | Один раз, під час ініціалізації `val`       |
| Можна передати як аргумент | Через eta-expansion                      | Так                                          |
| Має тип `FunctionX`        | Ні                                       | Так                                          |
| Замикання (closure)        | Немає                                    | Є — зберігає контекст змінних                |
| Жадність / Лінивість       | Лінива (виконується при виклику)         | Жадна (function object створюється одразу)  |
| Кешує результат            | Ні                                       | Ні                                           |
| Результат при виклику      | Може бути різним                         | Теж може бути різним                         |

---

## 🔄 Eta-expansion

```scala
def add(x: Int, y: Int): Int = x + y
val addFn: (Int, Int) => Int = add // автоматично розгортається в функцію
```

---

## 🔹 Типи функцій

```scala
Int => String           // Function1[Int, String]
(Int, Int) => Boolean   // Function2[Int, Int, Boolean]
() => Unit              // Function0[Unit]
```

---

## ✅ Юніт-тести

```scala
import org.scalatest.funsuite.AnyFunSuite

class FunctionVariablesTest extends AnyFunSuite:

  test("assigning function to a variable") {
    val square = (x: Int) => x * x
    assert(square(4) == 16)
  }

  test("using function variable in higher-order function") {
    val isEven = (x: Int) => x % 2 == 0
    val result = List(1, 2, 3, 4).filter(isEven)
    assert(result == List(2, 4))
  }

  test("eta expansion from method to function") {
    def add(x: Int, y: Int): Int = x + y
    val addFn: (Int, Int) => Int = add
    assert(addFn(2, 3) == 5)
  }

  test("val function captures closure") {
    var count = 0
    val increment: () => Int = () => {
      count += 1
      count
    }
    assert(increment() == 1)
    assert(increment() == 2)
  }
```

---

## ❓ Інтерв’ю-питання і відповіді

### 1. У чому різниця між `def` і `val` функцією?

**Відповідь:** `def` — це метод, який не є об’єктом `FunctionX`. Його можна викликати напряму або передавати з eta-expansion. `val` з функцією — це вже значення, яке реалізує `FunctionX`, може бути передане як аргумент, має замикання, і створюється один раз при ініціалізації.

---

### 2. Чи зберігає `val` функція результат?

**Відповідь:** Ні. Але сама функція (об’єкт типу `FunctionX`) створюється один раз, а її виконання — щоразу при виклику. Вона може запам’ятовувати значення з оточення (через closure).

---

### 3. Як передати функцію як аргумент?

**Відповідь:** Через функцію у `val`: `list.map(f)` або `list.map(x => x * 2)`. `def` також можна передати, якщо відбудеться eta-expansion.

---

### 4. Як створити функцію без параметрів?

```scala
val greet: () => String = () => "Hello"
greet() // "Hello"
```

---

Склала: Scala 3 Mentor 😎