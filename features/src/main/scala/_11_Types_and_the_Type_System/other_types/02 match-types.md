# Match Types у Scala 3

## 🔍 Що це таке?

**Match Types** — це тип-функції, що дозволяють визначати типи шляхом патерн-матчінгу над вхідними типами. Вони схожі на `match` у значеннях, але працюють **на рівні типів**.

---

## 🧩 Приклад

```scala
type Element[X] = X match
  case String => Char
  case Array[t] => t
  case Iterable[t] => t
  case _ => X
```

Цей `match type` описує логіку:
- Якщо `X` — `String`, то `Element[X] = Char`
- Якщо `X` — масив, то `Element[X] = t`, тобто тип елементу масиву
- Якщо `X` — колекція, то тип її елементу
- Інакше просто `X`

---

## ✅ Приклад використання

```scala
val e1: Element[String] = 'a'          // Char
val e2: Element[Array[Int]] = 42       // Int
val e3: Element[List[Double]] = 3.14   // Double
val e4: Element[Boolean] = true        // Boolean
```

---

## 🧪 Юніт-тест

```scala
"Element match type" should "resolve Char for String" in {
  val e: Element[String] = 'a'
  e shouldBe a [Char]
}
```

---

## 🧠 Для чого це?

Match types дозволяють:
- Виводити **умовні типи** в залежності від вхідного типу
- Писати **універсальні API**, де типи адаптуються до даних
- Замінюють деякі шаблони з shapeless / типами-контейнерами

---

## 🤔 Питання для співбесіди

### 1. Що таке `Match Types`?
> Це тип-функції з pattern matching на рівні типів, що дозволяють визначати результат типу в залежності від вхідного типу.

### 2. У чому різниця між `Match Type` і pattern matching у функціях?
> Pattern matching у функціях працює з **значеннями**, а `match types` — з **типами під час компіляції**.

### 3. Як можна використати `Match Types` в API?
> Наприклад, для виведення типу елементу з колекції або побудови умовного типу результату залежно від параметра.

### 4. Чи можна вкладати `match types`?
> Так, вони можуть бути вкладеними та рекурсивними, але з обмеженнями на термінування.

---

## 📂 Пов’язані файли

- `MatchTypes.scala` — реалізація
- `MatchTypesTest.scala` — юніт-тест

---

## 📚 Додатково

- [Dotty docs – Match Types](https://docs.scala-lang.org/scala3/reference/new-types/match-types.html)