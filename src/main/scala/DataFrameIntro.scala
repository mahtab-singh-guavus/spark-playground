import org.apache.spark.sql.SparkSession

object DataFrameIntro extends App {

  // Entry Spark framework
  val spark = SparkSession.builder()
    .appName("Data Frame Intro")
    .config("spark.master","local")
    .getOrCreate()


  // DataFrame = Data + Schema

  // Create DF by reading some data
  val carsDF = spark.read
    .format("json")
    .options( Map(
        "inferSchema" -> "true",
        "compression" -> "uncompressed"
      )
    )
    .load()

  carsDF.show()
  carsDF.printSchema()


  /*
  1. Write a higher order function life someof (list, fun)
  2. Implicit to convert a String to a Cars object and call drive() method
  3. Read movies.json as a DataFrame
	print its schema
	count the number of rows
	4. Create a DataFrame from a Seq of Tuples
   */

}
