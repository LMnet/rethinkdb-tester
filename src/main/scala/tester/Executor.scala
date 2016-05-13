package tester

import akka.actor.{ActorRef, Props}

import scala.concurrent.Future
import scala.util.Failure

object Executor {

  def props(dbConnection: ActorRef, dbName: String, query: (DbContext) ⇒ Future[Unit]): Props = {
    Props(new Executor(dbConnection, dbName: String, query))
  }
}

class Executor(val dbConnection: ActorRef, val dbName: String, query: (DbContext) ⇒ Future[Unit]) extends DbContext {

  override def receive: Receive = {
    case DbContext.ExecuteQuery ⇒
      query(this).andThen {
        case Failure(e) ⇒ Console.err.println(e)
      }
  }
}
