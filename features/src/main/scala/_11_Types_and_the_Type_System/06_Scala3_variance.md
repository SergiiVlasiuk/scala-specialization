# Варіантність (`Variance`): інваріантність (`invariance`), коваріантність (`covariance`) і контрваріантність (`contravariance`)

## `Variance`: `+T`/`T`/`-T` `covariance`/`invariance`/`contravariance`

- **Варіантність** — це властивість типів, яка визначає, чи зберігається співвідношення підтипів між параметризованими типами. Наприклад, якщо `Dog` є підтипом `Animal`, то чи буде `Box[Dog]` підтипом `Box[Animal]`?
- **Коваріантність** (`+A`) — це коли тип `C[Dog]` вважається підтипом `C[Animal]`, якщо `Dog <: Animal`. Такий тип **безпечно використовувати лише для читання**.
- **Контрваріантність** (`-A`) — це коли тип `C[Animal]` вважається підтипом `C[Dog]`, якщо `Dog <: Animal`. Такий тип **безпечно використовувати лише для споживання (параметри)**.
- **Інваріантність** (`A`) — це коли `C[Dog]` та `C[Animal]` вважаються повністю несумісними типами, навіть якщо `Dog <: Animal`.


У Scala **варіантність** описує, як співвідношення типів між складнішими структурами (наприклад, `Box[Dog]`) пов'язане зі співвідношенням їхніх компонентних типів (`Dog` → `Animal`). Є три типи:

```scala 3
class Animal
class Dog extends Animal
class Cat extends Animal
```

---

### 📊 Таблиця правил варіантності в Scala


| Тип варіантності  | Позначення | Зв’язок підтипу                                                                               |Коли використовується                              |  Виробляє (повертає)? | Споживає (приймає)? | Приклади                | Обмеження                                                              |
| ----------------- | ---------- |-----------------------------------------------------------------------------------------------| -------------------- | ------------------- | -------------------------------------------------- |-------------------------| ---------------------------------------------------------------------- |
| Інваріантність    | `A`        | ❌ Не зберігається<br/>`Box[Dog]` не є підтипом `Box[Animal]`<br/>(`Box[Dog]` ≠ `Box[Animal]`) | Коли тип і читає, і записує<br/>Коли контейнер і виробляє, і споживає значення (наприклад, Mutable Box).                        | ✅ Так                | ✅ Так               | `var` поля, мутабельні колекції, `Box` з `get` і `set`    | Найбезпечніший, але найменш гнучкий. `Box[Dog]` і `Box[Animal]` - це абсолютно різні типи              |
| Коваріантність    | `+A`       | ✅ Зберігається<br/>бо `Box[Dog]` є підтипом `Box[Animal]`<br/>`Box[Dog]` <: `Box[Animal]`)    | Коли тип тільки читається<br/>Коли контейнер лише виробляє значення (наприклад, Producer, Immutable List).                          |✅ Так                | ❌ Ні                |`List[+A]`,<br/>`Seq[+A]`,<br/>`Option[+A]`,<br/>`trait Producer[+A] { def get: A }` | Не можна використовувати тип `A` у позиціях, де він "споживається" (наприклад, у параметрах методів). Компілятор видасть помилку           |
| Контрваріантність | `-A`       | 🔁 Обертається<br/>`Box[Animal]` є підтипом `Box[Dog]` (`Box` є контрваріантним типом)<br/>`Box[Animal]` <: `Box[Dog]`                                                 | Коли тип тільки споживає дані (наприклад, функції)<br/>Коли контейнер лише споживає значення (наприклад, Consumer, Function Input). |❌ Ні                 | ✅ Так               | `Function1[-A, +B]` (для `A`), `trait Consumer[-A] { def process(a: A): Unit }`.    | Не можна використовувати тип A у позиціях, де він "виробляється" (наприклад, у поверненні методів). Компілятор видасть помилку. |

---

### Пояснення та діаграма позицій

Щоб краще візуалізувати правила "виробництва" та "споживання", уявіть собі тип `A` у функції:

```scala 3
trait MyTrait[VarianceTypeParameter] {
  def producerMethod: VarianceTypeParameter // Позиція "виробника" (повертає)
  def consumerMethod(param: VarianceTypeParameter): Unit // Позиція "споживача" (приймає)
  var field: VarianceTypeParameter // Позиція "і виробника, і споживача"
}
```

---

### Спрощена "Діаграма Потоку Типів"

```
                 +----------------------------+
                 |    КОНТЕЙНЕР (TRAIT/CLASS) |
                 +----------------------------+
                          |
                          v
                    +--------------+
                    |    Тип A     |
                    +--------------+
          (де він може бути використаний?)
                      /      \
                     /        \
                    v          v
          "ВИРОБЛЯЄ"         "СПОЖИВАЄ"
       (Позитивна позиція) (Негативна позиція)
    (Повернення, колекції) (Параметри, функціональні входи)
```

- **Коваріантність** (`+A`): Тип `A` може бути лише у "виробничих" позиціях. Думайте, що дані виходять з контейнера. `Container[+A] def get: A` (ОК) `def put(value: A)` (НЕ ОК)
- **Контрваріантність** (`-A`): Тип A може бути лише у "споживчих" позиціях. Думайте, що дані входять у контейнер. `Container[-A] def get: A` (НЕ ОК) `def put(value: A)` (ОК)
- **Інваріантність** (`A`): Тип `A` може бути як у "виробничих", так і в "споживчих" позиціях. Дані входять і виходять. `Container[A] def get: A` (ОК) `def put(value: A)` (ОК)
---
