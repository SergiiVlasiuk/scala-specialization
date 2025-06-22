# Top-level Definitions у Scala 3

## Що це таке?

Top-level Definitions — це можливість оголошувати `val`, `def`, `class`, `object` тощо *поза* об'єктами (`object`) або класами.

## Що можна оголошувати?

- `val`, `var`
- `def`
- `type`
- `class`, `trait`, `object`
- `given`, `using`, `extension`
- `import`, `package`

## Приклад

```scala
def greet(name: String): String =
  s"Hello, $name!"

val appName = "ToplevelDemo"

type Username = String

class User(val name: String):
  def greet(): String = s"Hello, $name!"

object User:
  def apply(name: String) = new User(name)
```

## Переваги

- Менше обгорток та шаблонного коду
- Краща читабельність
- Простіше рефакторити
- Зручно для невеликих утиліт і бібліотек

## Обмеження

- Доступне лише в Scala 3
- У байткоді все одно обгортається у клас
