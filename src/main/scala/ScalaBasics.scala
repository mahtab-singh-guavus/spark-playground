import scala.util.Random

object ScalaBasics extends App {

  // Main Function, App

  // Val vs var, type inference
  val age = 30
  // literals, scala.Predef, Scala Types

  // Operator overloading

  // expressions
  val result = if (age > 18) "ok" else "no"

  // OOP Classes and Traits
  abstract class Animal
  class Dog(name:String) extends Animal
  trait Carnivore {
    def eat(): Unit
  }
  class Crocodile extends Animal with Carnivore {
    override def eat(): Unit = println("crunch")
  }



  // Case Class



  // Singleton

  object Logger {
    def info(message: String): Unit = println(s"INFO: $message")
  }

  class Project(name: String, daysToComplete: Int)

  class Test {
    val project1 = new Project("TPS Reports", 1)
    val project2 = new Project("Website redesign", 5)
    Logger.info("Created projects")  // Prints "INFO: Created projects"
  }

  // Companion Object
  import scala.math._

  case class Circle(radius: Double) {
    def area: Double = Circle.calculateArea(radius)
  }

  object Circle {
    private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
  }

  // List and Tuple
  List(1,2,3)
  1 to 10

  val p = ("Mahtab", "Singh", 38)

  // Functional
  // function literals
  val name : String = "Mahtab"



  def even(x:Int) = {
    x % 2 == 0
  }

  // Higher order functions

  val l = List(1,2,3,4,5)

  // List only even number
  println(l.filter( even ))
  println(l.filter( (x:Int) => x % 2 == 0 ) )
  println(l.filter( _ % 2 == 0 ) )

  println( l.map(( (x:Int) => x * x )) )

  println( l.reduce( (x:Int,y:Int) => x * y ) )

  def sumof(l:List[Int], f:(Int) => Int) = {
    l.map(f).reduce( _ + _ )
  }

  sumof(l, x => x )
  sumof(l, x => x * x )
  sumof(l, x => x * x * x )

 /*
  1, 2   => 3
  3, 3   => 6
  6, 4   => 10
  10, 5  => 15
*/

  // Pattern Matching
  val x = Random.nextInt(10)

  x match {
    case 1 => "First"
    case 2 => "Second"
    case _ => "Doesnt matter"
  }

  try {
    throw new NumberFormatException
  }catch{
    case e: NumberFormatException => "do something"
    case _: NullPointerException => "So something else "
    case _:Throwable => "Exception "
  }

  abstract class Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification

  def showNotification(notification: Notification): String = {
    notification match {
      case Email(sender, title, _) =>
        s"You got an email from $sender with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"You received a Voice Recording from $name! Click the link to hear it: $link"
    }
  }
  val someSms = SMS("12345", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")

  println(showNotification(someSms))


  def toInt(in: String): Option[Int] = {
    try {
      Some(Integer.parseInt(in.trim))
    } catch {
      case e: NumberFormatException => None
    }
  }

  toInt("Some String") match {
    case Some(i) => println(i)
    case None => println("That didn't work.")
  }

  val bag = List("1", "2", "foo", "3", "bar")

  val sum = bag.flatMap(toInt).sum

  // Implicits
  // 1
//  def stringToPerson(name:String) = new Person(name)
//  stringToPerson("Mahtab").greet()

  // 2
//  implicit class Person(name:String) {
//    def greet() = s"Hello $name"
//  }
//  "Mahtab".greet()

  // 3
//  class Person(name:String) {
//        def greet() = s"Hello $name"
//  }
//  implicit def stringToPerson(name:String) = new Person(name)
//  "Mahtab".greet()

  //

}