package _07_Functions.functional_dsl

case class Request(method: String, path: String, body: String)
case class Response(status: Int, body: String)

// Тип маршруту: часткова функція
// Вона або обробляє запит, або ні
type Route = PartialFunction[Request, Response]

object Router:

  // Каррінгований метод для створення маршруту
  def route(method: String, path: String)(handler: Request => Response): Route = {
    case req if req.method == method && req.path == path => handler(req)
  }

  // Комбінування двох маршрутів
  def combine(r1: Route, r2: Route): Route = r1.orElse(r2)

  // Побудова програми: з маршруту створюється повна функція Request => Response
  def makeApp(router: Route): Request => Response = { req =>
    router.applyOrElse(req, (_: Request) => Response(404, "Default Not Found"))
  }

  // Інжекція залежностей — логування
  def routeWithLogging(logger: String => Unit)(method: String, path: String)(handler: Request => Response): Route = {
    case req if req.method == method && req.path == path =>
      logger(s"[LOG] ${req.method} ${req.path}")
      handler(req)
  }

  // Middleware: змінює Request перед обробкою
  type Middleware = Request => Request
  type Handler = Request => Response

  def withMiddleware(mw: Middleware)(handler: Handler): Handler = { req =>
    handler(mw(req))
  }

  // Not found fallback
  val notFound: Route = {
    case _ => Response(404, "Not Found")
  }

end Router
