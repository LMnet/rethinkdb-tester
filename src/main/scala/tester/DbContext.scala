package tester

import akka.util.Timeout
import pushka.Ast
import reql.akka.ReqlActor
import reql.dsl.ReqlArg
import reql.pushka.PushkaReqlContext

import scala.concurrent.Future
import scala.concurrent.duration._

object DbContext {
  case object ExecuteQuery
}

trait DbContext extends ReqlActor[Ast] with PushkaReqlContext {

  val dbName: String

  val db = r.db(dbName)

  val queryTimeout: Timeout = Timeout(1.second)

  implicit val dispatcher = context.dispatcher

  def runAndMap[B](query: ReqlArg)(f: PartialFunction[Ast, B]): Future[B] = {
    query.runA map { ast ⇒
      if (f.isDefinedAt(ast)) {
        f(ast)
      } else {
        throw new Exception
      }
    }
  }
}
