/*
Created by Allyson Martins
Date: 06/28/2019
allyson.martinz@gmail.com
*/

package example.usage

import com.typesafe.scalalogging.LazyLogging
import typed.definition.Typed._

object TypeOperations extends App with LazyLogging{

  private def excludeOr[T: (String ¬| Int)#check](x:T) = x match{
    case a:String => println("String => " + a)
    case b:Int => println("Int => " + b)
    case c:Double => println("Double => " + c)
  }
  excludeOr(8.0) // Double simulate success
  excludeOr("test") //simulate error

  private def typeOr[T: (String | Int)#check](x:T) = x match{
    case a:String => println("String => " + a)
    case b: Int => println("Int => " + b)
    case c:Double => println("Double => " + c)
  }
  typeOr("test") //simulate success
  typeOr(3.0)// simulate error

  private def notOption[T: |¬|[Option[_]]#check](x:T) = {
    println("Value => " + x)
  }
  notOption("test") //simulate success
  notOption(Some("teste")) // simulate error

  private def controlInOut[T: (String | Int)#check, K: (String ¬|| Int)#check](x:T,y:K):K = { // control from sub and upper type
   y
  }
  val a: Any = 3.0
  val b = controlInOut("1",3.0) // simulate success
  controlInOut("1",a) //simulate error
  println(b)

  private def notFunInput[T: |¬|[_ => _]#check](x:T) = {
    println(x)
  }

  notFunInput("test") //simulate success
  notFunInput((x:Int) => x + 5) //simulate error


  private def notListEmpty[T: |¬|[List[Nothing]]#check](x: T): Unit = {
    print("aaaaa")
  }

  notListEmpty(List(1)) // success
  notListEmpty(List.empty) // error

}
