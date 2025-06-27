# Generics in Scala 3

## Introduction
Generics allow you to write flexible and reusable code by abstracting over types. Instead of writing multiple versions of a method or class for each type, you can write a generic one.

---

## Basic Example: Generic Method
```scala
def identity[A](value: A): A = value

val intId = identity(42)          // Int
val stringId = identity("Scala") // String
```
Here, `A` is a type parameter. The method returns whatever it receives.

---

## Generic Class
```scala
class Box[A](val value: A):
  def get: A = value

val intBox = Box(10)
val strBox = Box("hello")
```
You can create instances with different types using the same class definition.

---

## 📘 Таблиця-конспект: Обмеження типів (Type Bounds) у Scala

| Позначення                    | Назва Обмеження                          | Значення (Що це означає?)                                                                                     | Приклад Використання                                                                                                              | Пояснення та Навіщо це потрібно                                                                                                                                                                                                                                                                                                  |
|------------------------------|------------------------------------------|----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `A <: B`                     | Верхнє обмеження *(Upper Bound)*         | Тип `A` повинен бути підтипом типу `B`.                                                                       | `def process[A <: Animal](item: A): Unit`                                                                                         | Це означає, що `A` може бути `Animal` або будь-яким його підкласом (`Dog`, `Cat`). Це гарантує, що `item` має всі методи та поля `Animal`. <br><br>**Навіщо:** Обмежує узагальнений тип до певного супертилу або його підтипів. Дозволяє вам безпечно викликати методи, визначені в `B`, для об'єктів типу `A`.                     |
| `A >: B`                     | Нижнє обмеження *(Lower Bound)*         | Тип `A` повинен бути супертилом типу `B`.                                                                     | `def addElement[A >: Dog](list: List[A], elem: A): List[A]`                                                                       | Це означає, що `A` може бути `Dog`, `Animal`, `AnyRef`, `Any`. Це гарантує, що `Dog` є підтипом `A`. <br><br>**Навіщо:** Дозволяє вам зберегти коваріантність (або її подобу) у певних сценаріях, особливо при роботі з мутабельними колекціями або при додаванні елементів до коваріантних типів. Важливо для збереження інформації про тип найменш загального супертилу. |
| `A <: Comparable[A]`         | Вид/Обмеження за ознакою *(View/Context Bound)* | Тип `A` повинен бути підтипом `Comparable[A]`, або має існувати неявне перетворення (*implicit conversion*) з `A` на `Comparable[A]`. (У Scala 3 це частіше виражається через `using` або `given` параметри). | `def max[A <: Comparable[A]](x: A, y: A): A` *(Scala 2)* <br> `def max[A : Ordering](x: A, y: A): A` *(Scala 2/3 з context bound)* <br> `def max[A](x: A, y: A)(using ord: Ordering[A]): A` *(Scala 3 з using)* | Це гарантує, що тип `A` має метод `compareTo` або може бути порівняний. <br><br>**Навіщо:** Дозволяє використовувати методи, які вимагають певної функціональності від типу, без необхідності безпосередньо розширювати цей тип. Дуже часто використовується для трейтів-типокласів, таких як `Ordering`, `Numeric` тощо, де потрібні неявні екземпляри для певного типу. |

---

- `A <: Comparable[A]` (View Bound): У Scala 3 `View Bounds` (обмеження за ознакою) були депрекейтед (deprecated) на користь `Context Bounds` (`A: TypeClass`) та `Implicit Parameter Clauses` (`(using TypeClass[A])` або `(implicit TypeClass[A])` у Scala 2).
    - `def max[A: Ordering](x: A, y: A)`: `A` - це цукровий синтаксис (syntactic sugar) для `def max[A](x: A, y: A)(using ord: Ordering[A]): A`.
    - Ці механізми дозволяють компілятору автоматично знаходити неявний екземпляр певного типокласу (наприклад, `Ordering[A]`) для типу `A`. Це дуже потужно для узагальненого програмування.
- **Комбінація обмежень**: Ви можете комбінувати ці обмеження. Наприклад:
`def process[A >: Dog <: Animal](item: A): Unit` - означає, що `A` повинен бути супертипом `Dog` і підтипом `Animal`. Тобто `A` може бути `Dog` або `Animal`.


## Bounded Type Parameters

You can restrict type parameters to certain types:
```scala
def upperBoundExample[A <: Comparable[A]](a1: A, a2: A): A =
  if a1.compareTo(a2) >= 0 then a1 else a2
```
This function works only with types that implement `Comparable`.

---

## Type Constraints and Context Bounds
```scala
def maxList[A: Ordering](list: List[A]): A =
  list.max
```
This uses a context bound, requiring an `Ordering[A]` to be in scope.

---

## Using Type Parameters in Traits
```scala
trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]
```
This shows higher-kinded types. Type `F` is itself a type constructor.

---

# Tests
```scala
import org.scalatest.funsuite.AnyFunSuite

class GenericsTest extends AnyFunSuite:

  test("identity returns input"):
    assert(identity(123) == 123)
    assert(identity("abc") == "abc")

  test("Box stores and returns value"):
    val box = Box(42)
    assert(box.get == 42)

  test("upperBoundExample returns max"):
    assert(upperBoundExample("abc", "xyz") == "xyz")

  test("maxList works for Int list"):
    assert(maxList(List(1, 3, 2)) == 3)
```

---

# Interview Questions & Answers

### Q1: What are generics in Scala?
**A:** Generics allow you to write code that is parameterized by types.

### Q2: What are bounded type parameters?
**A:** Bounded type parameters restrict the range of types that can be used, e.g., `A <: Comparable[A]`.

### Q3: What is variance in Scala?
**A:** Variance controls subtyping relationships for generic types. `+A` is covariant, `-A` is contravariant.

### Q4: What is a higher-kinded type?
**A:** A type that abstracts over type constructors (like `F[_]`), used in type classes like `Functor`, `Monad`, etc.

### Q5: When do you use context bounds?
**A:** When you want to require that a type class instance (like `Ordering`) is available in scope for a type.

### Q6: Can type parameters have multiple bounds?
**A:** Yes, using combined bounds: `A <: B & C`.

### Q7: What is the difference between `A <: B` and `A >: B`?
**A:** `A <: B` means A must be a subtype of B (upper bound), `A >: B` means A must be a supertype of B (lower bound).

---

# Summary
- Generics increase code reuse and type safety.
- Use type parameters in methods, classes, and traits.
- Bounds and variance control type relationships.
- Higher-kinded types and context bounds are powerful tools in Scala’s type system.

