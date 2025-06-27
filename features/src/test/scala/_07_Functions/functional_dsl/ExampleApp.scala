package _07_Functions.functional_dsl

// ---------- DSL-стиль використання

object ExampleApp:
  import _07_Functions.functional_dsl.Router.*

  val logToConsole: String => Unit = println

  val app = makeApp(
    combine(
      route("GET", "/health")(_ => Response(200, "OK")),
      routeWithLogging(logToConsole)("POST", "/echo")(req => Response(200, req.body))
    )
  )

  // Тестовий запуск
  val testRequests = List(
    Request("GET", "/health", ""),
    Request("POST", "/echo", "Hello!"),
    Request("DELETE", "/unknown", "")
  )

  @main def run(): Unit =
    testRequests.foreach { req =>
      val resp = app(req)
      println(s"${req.method} ${req.path} => ${resp.status} ${resp.body}")
    }
