# for-yield

📌 Загальна таблиця:

|Синтаксис for | Синтаксис методів | Операція |
|-|-|-|
| for (x <- xs) yield f(x)|	xs.map(f)|	map|
|for (x <- xs if p(x)) yield f(x)|	xs.withFilter(p).map(f)|	filter|
|for (x <- xs; y <- ys) yield f(x,y)|	xs.flatMap(x => ys.map(y => f(x,y)))|	flatMap|
|for {x <- xs; if p(x); y <- ys} yield f(x,y)|	xs.withFilter(p).flatMap(x => ys.map(...))|	комбіновано|



