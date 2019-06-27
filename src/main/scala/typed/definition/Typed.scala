package typed.definition

import scala.concurrent.Future

object Typed {

  def unexpected = sys.error("Error, you can't use theses types")

  trait <:!<[A,B] // sub type operator
  implicit def notSubT[A,B]: A <:!< B = null
  implicit def notSubAmb[A,B >: A]: A <:!< B = unexpected
  implicit def notSubAmb2[A,B >: A]: A <:!< B = unexpected

  trait >:!>[A,B] // upper type operator
  implicit def notSupT[A,B]: A >:!> B = null
  implicit def notSupAmb[A,B <: A]: A >:!> B = unexpected
  implicit def notSupAmb2[A,B <: A]: A >:!> B = unexpected

  type and[T,U] = T with U // and operator
  type ¬[T] = T => Nothing // negation type : ¬true => false
  type ¬¬[T] = ¬[¬[T]] // double negation type : ¬¬true => true
  type or[T,U] = ¬[¬[T] and ¬[U]] // or operator
  type |[T,U] = {type check[X] = ¬¬[X] <:< (T or U)} // or type operator
  type ¬|[T,U] = {type check[X] = ¬[¬[X]] <:!< (T or U)}  // negation of (or) type operation
  type ¬||[T,U] = {type check[X] =  X >:!> (T and U) } // negation of uppertype and subtype operation
  type |¬|[T] = {type check[X] = X <:!< T} //negation of type
  type ¬>>[T] = {type check[X] = X <:!< Future[T] } // not be future of type
  type >>[T] = {type check[X] = X <:< Future[T] } // must be future
  type |>>[T,U] = {type check[X] = ¬¬[X] <:< (Future[T] or Future[U]) } //  be future
  type ¬|>>[T,U] = {type check[X] = ¬¬[X] <:!< (Future[T] or Future[U]) } // not be future
  type !>>[T] = {type check[X] = ¬¬[X] <:!< Future[_]}




}
