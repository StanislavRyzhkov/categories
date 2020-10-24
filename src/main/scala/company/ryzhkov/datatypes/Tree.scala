package company.ryzhkov.datatypes

sealed trait Tree[A] {
  override def toString: String =
    this match {
      case Leaf(a)                =>
        a.toString
      case Branch(left, a, right) =>
        s"${left.toString} : ${a.toString} : ${right.toString}"
    }
}

case class Leaf[A](a: A) extends Tree[A]
case class Branch[A](left: Tree[A], a: A, right: Tree[A]) extends Tree[A]
