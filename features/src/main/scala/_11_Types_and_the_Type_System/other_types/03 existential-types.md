# Existential Types у Scala 3

## 🔍 Що це таке?

**Existential Types** — це типи, які містять **приховані параметри типу**. Вони дозволяють абстрагуватися від конкретного типу і працювати з «якимось» типом без знання його конкретного значення.

У Scala 3 замість складного синтаксису з `forSome` використовується простіший запис:

```scala
Container[?] // означає "Container з деяким типом T"
```

---

## 🧩 Приклад

```scala
trait Container[T]:
  def get: T

def printUnknown(container: Container[?]): String =
  val value = container.get
  s"Got value: $value"

class StringContainer extends Container[String]:
  def get: String = "Scala"

class IntContainer extends Container[Int]:
  def get: Int = 42
```

---

## ✅ Як це працює?

- `Container[?]` — це контейнер з **невідомим типом**, який нам неважливо знати.
- Метод `printUnknown` приймає будь-який контейнер, але **не намагається оперувати з типом**, лише викликає `get` і друкує значення.

---

## 🧪 Юніт-тест

```scala
"printUnknown" should "print value from StringContainer" in {
  val sc = new StringContainer
  printUnknown(sc) should include ("Scala")
}

it should "print value from IntContainer" in {
  val ic = new IntContainer
  printUnknown(ic) should include ("42")
}
```

---

## 🤔 Для чого це?

- Якщо ти **не хочеш або не можеш** знати точний тип у generics, але все одно хочеш з ним працювати.
- Для написання **універсальних API**.
- Часто використовується в бібліотеках, фреймворках, DI контейнерах тощо.

---

## 💡 Типовий приклад із колекціями

```scala
val listOfUnknowns: List[?] = List(1, 2, 3)
```

---

## 🧠 Питання для співбесіди

### 1. Що таке existential type у Scala?
> Тип, який дозволяє приховати конкретний параметр типу — наприклад, `List[?]`, `Map[String, ?]`.

### 2. Як позначаються existential types у Scala 3?
> Використовується знак питання `?` замість `forSome`, як було у Scala 2.

### 3. Яка користь від existential types?
> Вони дозволяють узагальнювати код, не знаючи точного типу — працювати з "якимось типом" у безпечний спосіб.

### 4. Чи можна модифікувати значення, коли тип невідомий?
> Ні, адже ти не знаєш, який тип там. Ти можеш лише викликати методи, які не залежать від конкретного типу.

---

## 📚 Додаткові ресурси

- [Dotty Documentation – Existential Types](https://docs.scala-lang.org/scala3/reference/new-types/existential-types.html)
