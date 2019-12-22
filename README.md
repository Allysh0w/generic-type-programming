# Generic type programming with scala

## Usage
- ¬¬[T]    = Negation from type negation
- |[T,U]   = Or operator
- ¬|[T,U]  = Negation of (Or) type operation
- ¬||[T,U] =  Negation of (Or) from uppertype and subtype operation
- |¬|[T]   = Negation type
- ¬>>[T]   = exclusive future type
- ">>[T]"    = Must be future of T type
- |>>[T,U] = Or operator of future type operation
- ¬|>>[T,U] = Negation of (Or) from future type operation
- !>>[T] = Negation from future type operation

### Example: 

```scala
private def excludeOr[T: (String ¬| Int)#check](x:T) = x match{
    case a:String => logger.info("String => " + a)
    case b:Int => logger.info("Int => " + b)
    case c:Double => logger.info("Double => " + c)
  }
  excludeOr(8.0) // Double simulate success
  excludeOr("test") //simulate error
  
```

Using Future: 
```scala
private def excludeTypeOfFuture[T: (Double ¬|>> Int)#check](x:T) = x match {
    case s: Future[String] => s.map{ x => logger.info("String => " + x)}
    case i:Future[Int] => i.map{ x => logger.info("Int => " + x)}
    case d:Future[Double] => d.map{ x => logger.info("Double => " + x)}
    case _ => logger.info("Another type")
  }
  excludeTypeOfFuture(Future("test")) // simulate success
  excludeTypeOfFuture(Future(3)) // simulate error

```

References: http://milessabin.com/blog/2011/06/09/scala-union-types-curry-howard/
