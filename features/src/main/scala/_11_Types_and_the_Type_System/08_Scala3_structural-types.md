# Structural Types

## Вступ

**Structural Types** — це типова система, що визначає тип об'єкта за його структурою, а не за ім'ям. Вони контрастують з номінальним типізуванням, де важливе ім'я типу.

## Приклади

```scala
package _11_Types_and_the_Type_System

import scala.reflect.Selectable.reflectiveSelectable

def closeQuietly(resource: { def close(): Unit }): Unit =
  try {
    resource.close()
  } catch {
    case _: Exception =>
  }

class File:
  def close(): Unit = println("File closed")

class Connection:
  def close(): Unit = println("Connection closed")

def printName(entity: { def name: String }): Unit = {
  println(entity.name)
}

val person = new {
  val name = "Serhii"
  val age = 30
}

val company = new {
  val name = "INTA"
  val location = "Kyiv"
}

def invokeClose(resource: Selectable { def close(): Unit }): Unit =
  try resource.close()
  catch { case _: Exception => () }
```

## Тести

```scala
package _11_Types_and_the_Type_System

import scala.reflect.Selectable.reflectiveSelectable

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class StructuralTypeSpec extends AnyFlatSpec with Matchers {

  "closeQuietly" should "call close method on File" in {
    val file: {def close(): Unit; def closed: Boolean} = new {
      var closed = false
      def close(): Unit = closed = true
    }
    closeQuietly(file)
    file.closed shouldBe true
  }

  it should "call close method on Connection" in {
    val conn: {def close(): Unit; def closed: Boolean} = new {
      var closed = false
      def close(): Unit = closed = true
    }
    closeQuietly(conn)
    conn.closed shouldBe true
  }

  "printName" should "print the name property" in {
    val out = new java.io.ByteArrayOutputStream()
    Console.withOut(out) {
      val entity: { def name: String } = new { val name = "TestName" }
      printName(entity)
    }
    out.toString should include ("TestName")
  }

  "invokeClose" should "call close on Selectable resource" in {
    class TestResource extends Selectable {
      var closed = false

      def close(): Unit = closed = true
    }

    val res = new TestResource
    invokeClose(res)
    res.closed shouldBe true
  }
}
```

## Питання для співбесіди з відповідями

**1. Що таке structural types?**  
Це типи, які визначаються структурою об'єкта (тобто, набором методів і полів), а не його ім'ям чи ієрархією наслідування. Наприклад, будь-який об'єкт із методом `def close(): Unit` може бути переданий до функції, яка очікує параметр типу `{ def close(): Unit }`.

---

**2. Чим structural typing відрізняється від nominal typing?**
- **Structural typing**: об'єкт вважається певного типу, якщо має потрібну структуру.
- **Nominal typing**: тип визначається ім'ям (наприклад, клас чи trait), незалежно від фактичної структури.

---

**3. Які є недоліки structural types у Scala?**
- Повільніші: використовують рефлексію, що має накладні витрати.
- Менш типобезпечні: помилки можуть проявлятися під час виконання.
- Складніші для читання і супроводу: типи неявно описані.

---

**4. Коли варто використовувати structural types?**
- Коли потрібно швидко прототипувати без створення зайвих інтерфейсів.
- Коли об'єкти з різних джерел мають однакову структуру, але не спільну суперкласову ієрархію.
- У тестах або тимчасових утилітах, де простота важливіша за продуктивність.