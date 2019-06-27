package example.usage


import akka.actor.ActorSystem
import com.typesafe.scalalogging.{LazyLogging}
import typed.definition.Typed._

import scala.concurrent.{ExecutionContextExecutor, Future}

object FutureOperations extends App with LazyLogging {
implicit val ec: ExecutionContextExecutor = ActorSystem("usage").dispatcher


  private def beFutureOfType[T: >>[Double]#check](x:T) =  {
    logger.info("Value => " + x)
  }
  beFutureOfType(Future(3.0)) // simulate success
  beFutureOfType(Future("")) // simulate error

  private def notBeFutureOfType[T: ¬>>[Double]#check](x:T) =  {
    logger.info("Value => " + x)
    }
  notBeFutureOfType(Future("")) // simulate success
  notBeFutureOfType(Future(3.0)) // simulate error

 private def orOfFuture[T: (Double |>> Int)#check](x:T) = x match {
    case s: Future[String] => s.map{ x => logger.info("String => " + x)}
    case i:Future[Int] => i.map{ x => logger.info("Int => " + x)}
    case d:Future[Double] => d.map{ x => logger.info("Double => " + x)}
    case _ => logger.info("Another type")
  }
  orOfFuture(Future(3)) // simulate success
  orOfFuture(Future("test")) // simulate error
//
 private def excludeTypeOfFuture[T: (Double ¬|>> Int)#check](x:T) = x match {
    case s: Future[String] => s.map{ x => logger.info("String => " + x)}
    case i:Future[Int] => i.map{ x => logger.info("Int => " + x)}
    case d:Future[Double] => d.map{ x => logger.info("Double => " + x)}
    case _ => logger.info("Another type")
  }
  excludeTypeOfFuture(Future("test")) // simulate success
  excludeTypeOfFuture(Future(3)) // simulate error

}
