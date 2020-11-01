package company.ryzhkov

import company.ryzhkov.datatypes.{Branch, Leaf, Tree}
import company.ryzhkov.instances.IdInstance.Id
import company.ryzhkov.typeclasses.Functor.FunctorOps
import org.scalatest.funsuite.AnyFunSuite

class FunctorTest extends AnyFunSuite {

  test("optionFunctor") {
    import company.ryzhkov.implicits.optionFunctorInstance

    val a: Option[Int] = Some(5)
    val b: Option[Int] = None

    def f(int: Int): String = s"This is $int"

    assert(a.fmap(f).contains("This is 5"))
    assert(b.fmap(f).isEmpty)
  }

  test("listFunctor") {
    import company.ryzhkov.implicits.listFunctorInstance

    val a = List(2, 3, 4)

    def f(i: Int): Int = i * i

    assert(4 == a.fmap(f).head)
    assert(9 == a.fmap(f)(1))
    assert(16 == a.fmap(f)(2))
  }

  test("treeFunctor") {
    import company.ryzhkov.implicits.treeFunctorInstance

    val tree: Tree[Int]  = Branch(Leaf(10), 15, Leaf(5))
    val tree2: Tree[Int] = Branch(Leaf(100), 150, Leaf(50))

    def f(i: Int): Int = i * 10

    assert(tree2 == tree.fmap(f))
  }

  test("tupleFunctor") {
    import company.ryzhkov.implicits.tupleFunctorInstance

    val t1 = (1, "Hello")
    val t2 = (1, "Hello, world!")

    val res = t1.fmap(_ + ", world!")

    assert(t2 == res)
  }

  test("functionFunctor") {
    import company.ryzhkov.implicits.functionFunctorInstance

    val f1: Int => Int     = x => if (x > 0) 1 else 0
    val f2: Int => Boolean = _ == 1

    val res = f1.fmap(f2)

    assert(!res(-10))
  }

  test("idFunctor") {
    import company.ryzhkov.implicits.idFunctorInstance

    val a: Id[Int] = 2

    assert("2" == a.fmap(_.toString))
    assert(5 == a.fmap(_ + 3))
  }

}
