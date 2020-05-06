import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

import scala.util.Random

object DFBasicFlights extends App {

val spark =   SparkSession.builder
    .appName("DF Basics")
    .config("spark.master","local")
    .config("spark.sql.shuffle.partitions", "10")  // parallelizm
    .config("spark.default.parallelism","5")
    .getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

  val flightData2015 = spark
    .read
    .option("inferSchema", "true")
    .option("header", "true")
    .csv("src/main/resources/data/flight-data/csv")


//  import org.apache.spark.sql.functions._
//  flightData2015.printSchema()
//  flightData2015.sort(desc("count")).take(2).foreach(println)

  flightData2015
    .groupBy("DEST_COUNTRY_NAME")
    .sum("count")
    .withColumnRenamed("sum(count)", "destination_total")
    .orderBy("destination_total")
    .limit(5)
    .show()

  Thread.sleep(1000000)
}