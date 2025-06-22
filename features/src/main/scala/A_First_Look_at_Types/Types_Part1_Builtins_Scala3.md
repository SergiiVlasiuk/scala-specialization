# A First Look at Types — Scala 3

## 1. Категорії типів

У Scala 3 всі типи належать до ієрархії:

- `Any` — найзагальніший тип
    - `AnyVal` — значення (Int, Double, Boolean, …)
    - `AnyRef` — об'єктні (класи, String, List, …)

## 2. Вбудовані типи
![img](img.png)
### Int, Double, Boolean
```scala
val age: Int = 30
val pi: Double = 3.14
val isScalaFun: Boolean = true
```

### Char, String
```scala
val letter: Char = 'A'
val name: String = "Alice"
```

### Unit
```scala
def sayHello(): Unit = println("Hello")
// Unit означає: функція нічого не повертає, має тип ()
```

## Властивості

- `AnyVal` зберігається в stack, `AnyRef` — у heap
- Unit має єдине значення: `()`
- Усі типи підпорядковуються `Any`
