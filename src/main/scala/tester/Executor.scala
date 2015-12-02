package tester

import akka.actor.{ActorRef, Props}

import scala.concurrent.Future

object Executor {

  def props(dbConnection: ActorRef, dbName: String, query: (DbContext) ⇒ Future[Unit]): Props = {
    Props(new Executor(dbConnection, dbName: String, query))
  }
}

class Executor(val dbConnection: ActorRef, val dbName: String, query: (DbContext) ⇒ Future[Unit]) extends DbContext {

  override def receive: Receive = {
    case DbContext.ExecuteQuery ⇒
      query(this).onComplete {
        case _ ⇒ context.system.terminate()
      }
  }
}
