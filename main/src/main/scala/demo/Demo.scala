package demo

trait T {
  val traitOnlyVal = 1
  val overriddenTraitVal = 2
  private val privateVal = 3
  def fooT = privateVal
}

trait T2 extends T {
  private val privateVal = 3
  def fooT2 = privateVal
  override val overriddenTraitVal = 2
}

class C extends T2 {
  override val overriddenTraitVal = 42
  private val privateVal = 3
  def fooC = privateVal
  val overriddenClassVal = 43
  val classOnlyVal = 42
}

class Demo extends C {
  private val privateVal = 3
  def fooD = privateVal
  override val overriddenTraitVal = 43
  override val overriddenClassVal = 53
}

object Demo {
  def main(args: Array[String]): Unit = {
    val d = new Demo
    val cls = classOf[Demo]

    val getter = cls.getDeclaredMethod("foo$impl")
    val x = getter.invoke(d)
    println(x)
    assert(x == -42)

    val setter = cls.getDeclaredMethod("foo$impl_$eq", java.lang.Integer.TYPE)
    setter.invoke(d,Int.box(-1))
    assert(getter.invoke(d) == -1)
  }
}
