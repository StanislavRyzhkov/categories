package company.ryzhkov.instances

import company.ryzhkov.datatypes.{Branch, Leaf, Tree}
import company.ryzhkov.typeclasses.Functor

class TreeInstance extends Functor[Tree] {

  override def fmap[A, B](fa: Tree[A])(f: A => B): Tree[B] =
    fa match {
      case Leaf(a)                => Leaf(f(a))
      case Branch(left, a, right) => Branch(fmap(left)(f), f(a), fmap(right)(f))
    }
}
