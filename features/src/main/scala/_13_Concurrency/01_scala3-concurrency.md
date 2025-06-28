# Concurrency in Scala 3

## Проблема, яку вирішує Concurrency

Concurrency (одночасність) дозволяє програмам виконувати декілька операцій паралельно, не блокуючи головний потік виконання. Це особливо важливо для масштабованих застосунків, які працюють з мережею, базами даних або довготривалими обчисленнями. У Scala 3 concurrency розкривається за допомогою `Future`, `async/await`, а також бібліотек типу `ZIO`, `cats-effect`.

---

## 1. Робочі приклади (Scala 3)

### Приклад 1: Базове використання Future

~~~scala
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def slowComputation(x: Int): Future[Int] = Future {
  Thread.sleep(1000)
  x * 2
}
~~~

### Приклад 2: Комбінація Future

~~~scala
val result: Future[Int] =
  for
    a <- slowComputation(2)
    b <- slowComputation(3)
  yield a + b
~~~

### Приклад 3: async/await (Scala 3)

~~~scala
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.async.Async.{async, await}

def computeAsync(): Future[Int] = async {
  val x = await(slowComputation(4))
  val y = await(slowComputation(5))
  x + y
}
~~~

---

## 2. Тести (з використанням ScalaTest)

~~~scala
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ConcurrencyTest extends AsyncFlatSpec with Matchers:

  def slowComputation(x: Int): Future[Int] = Future {
    Thread.sleep(100)
    x * 2
  }

  "slowComputation" should "double the number" in {
    slowComputation(2).map(_ shouldEqual 4)
  }

  it should "combine two computations" in {
    for
      a <- slowComputation(3)
      b <- slowComputation(4)
    yield a + b shouldEqual 14
  }
~~~

---

## 3. Узагальнююча таблиця

| Концепт        | Опис                                                                 |
|----------------|----------------------------------------------------------------------|
| `Future`       | Абстракція для обчислень, які виконуються асинхронно                |
| `ExecutionContext` | Контекст для виконання Future                                      |
| `async/await`  | Синтаксичний цукор у Scala 3 для написання асинхронного коду         |
| `ZIO` / `IO`   | Парадигма ефектів: потужніші та контрольованіші підходи до concurrency |

---

## 4. Питання для співбесіди

**Питання:** Чим `Future` відрізняється від `ZIO` чи `IO`?
- **Відповідь:** `Future` негайно виконується і не контролюється повністю програмістом. `ZIO`/`IO` — ледачі ефекти, які можна комбінувати, відкладати, запускати контрольовано.

**Питання:** Що таке `ExecutionContext`?
- **Відповідь:** Це абстракція для планування виконання завдань у `Future`.

**Питання:** Для чого використовують `async`/`await` у Scala 3?
- **Відповідь:** Для зручнішого запису асинхронного коду у стилі, подібному до синхронного.

**Питання:** Які є підводні камені при використанні `Future`?
- **Відповідь:** Side effects, thread starvation, shared mutable state, і відсутність повного контролю над порядком виконання.

---

## 5. Висновок

Concurrency — це ключовий інструмент сучасного розробника, особливо в екосистемі Scala. Scala 3 робить його доступнішим через синтаксис `async/await` і безшовну інтеграцію з `Future`. Проте для складніших задач краще використовувати FP-бібліотеки з контролем ефектів.