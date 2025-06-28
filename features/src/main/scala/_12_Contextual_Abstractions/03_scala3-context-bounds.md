# Context Bounds in Scala 3

---

## 1. Що таке Context Bounds?

**Context Bounds** — це **синтаксичний цукор** для визначення **неявної залежності** (типового класу) у сигнатурі функції.

Вони є коротшою формою для `using` параметрів з одним `given` типу.

---

## 2. Проблема, яку розвʼязують Context Bounds

У Scala часто використовують type class-и (наприклад, `Ordering`, `Show`, `Eq`). Для цього потрібно явно вказати:

~~~scala
def compare[A](a: A, b: A)(using ord: Ordering[A]): Int = ...
~~~

Context Bound дозволяє **спростити** запис, якщо потрібен лише `given` тип:

~~~scala
def compare[A: Ordering](a: A, b: A): Int = ...
~~~

Це особливо зручно, коли:
- Функція має **одну або кілька неявних залежностей**
- Ці залежності потрібні лише для делегування (через `summon`)

---

## 3. Робочі приклади (Scala 3)

### 📌 Приклад 1: Context Bound з `Ordering`

~~~scala
def compare[A: Ordering](a: A, b: A): Int =
  summon[Ordering[A]].compare(a, b)

@main def demo1() =
  println(compare(5, 3)) // > 0
~~~

---

### 📌 Приклад 2: З власним типом `Show`

~~~scala
trait Show[T]:
  def show(value: T): String

given Show[Int] with
  def show(value: Int): String = s"Int($value)"

def printValue[T: Show](value: T): String =
  summon[Show[T]].show(value)

@main def demo2() =
  println(printValue(42)) // Int(42)
~~~

---

## 4. Тести (ScalaTest)

~~~scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ContextBoundTest extends AnyFlatSpec with Matchers {

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  def printValue[T: Show](value: T): String =
    summon[Show[T]].show(value)

  def compare[A: Ordering](a: A, b: A): Int =
    summon[Ordering[A]].compare(a, b)

  "printValue" should "print Int with Show type class" in {
    printValue(10) shouldEqual "Int(10)"
  }

  "compare" should "compare values using Ordering" in {
    compare(5, 3) > 0 shouldBe true
    compare(3, 5) < 0 shouldBe true
    compare(5, 5) shouldEqual 0
  }
}
~~~

---

## 5. Питання та відповіді для співбесіди

### ❓ Що таке Context Bound у Scala?

🔹 Це синтаксична конструкція для `using`-параметрів типу. Записується як `A: TypeClass`.

---

### ❓ Що стоїть за `A: Show`?

🔹 Це скорочення для `(using s: Show[A])`. Щоб звернутись до `s`, використовують `summon[Show[A]]`.

---

### ❓ Як звернутися до `given` параметра, якщо він заданий через Context Bound?

🔹 Через `summon[TypeClass[A]]`.

---

### ❓ Яка різниця між Context Bound і Context Parameters?

🔹 Context Bound — лише синтаксичний цукор для `using` параметрів **одного типу**.

---

### ❓ Чи можна комбінувати Context Bound з іншими параметрами?

🔹 Так. Наприклад:

~~~scala
def doStuff[A: Ordering](list: List[A])(using show: Show[A]): Unit = ...
~~~

---

## 6. Переваги Context Bounds

- Коротший і чистіший код
- Підходить для типових type class патернів
- Працює з `summon` для отримання неявного значення

---
