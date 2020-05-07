//import org.apache.spark.sql.SparkSession
//import java.io.File
//
//object HiveSpark extends App{
//
//  val warehouseLocation = new File("spark-warehouse").getAbsolutePath
//
//  // creating a SparkSession
//  val spark = SparkSession.builder()
//    .appName("Hive and S3 Support")
//    .config("spark.master", "local")
//    .config("spark.sql.warehouse.dir", warehouseLocation)
//    .enableHiveSupport()
//    .getOrCreate()
//
//  import spark.implicits._
//  import spark.sql
//  sql("DROP TABLE src")
//  sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) row format delimited fields terminated by ','")
//  sql("LOAD DATA LOCAL INPATH 'src/main/resources/data/kv1.txt' OVERWRITE INTO TABLE src")
//
//  sql("FROM src SELECT key, value").collect().foreach(println)
//
//  spark.table("src").show()
//
//  spark.sparkContext
//       .hadoopConfiguration.set("fs.s3a.access.key", "AKIA4NIQXBQ6YEWB")
//   // Replace Key with your AWS secret key (You can find this on IAM
//  spark.sparkContext
//       .hadoopConfiguration.set("fs.s3a.secret.key", "ytZ8b0VvvhNhozq8hxXJ71kLYRPkYqLghr")
//  spark.sparkContext
//        .hadoopConfiguration.set("fs.s3a.endpoint", "s3.amazonaws.com")
//
//   val data = Seq(("James ","","Smith","36636","M",3000),
//        ("Michael ","Rose","","40288","M",4000),
//        ("Robert ","","Williams","42114","M",4000),
//        ("Maria ","Anne","Jones","39192","F",4000),
//        ("Jen","Mary","Brown","","F",-1)
//      )
//
//  import spark.implicits._
//  val df = data.toDF()
//
//  df.write.parquet("s3a://sparktrainingtest/parquet/people.parquet")
//  val parqDF = spark.read.parquet("s3a://sparktrainingtest/parquet/people.parquet")
//
//Thread.sleep(1000000)
//
//
//<dependencies>
//    <dependency>
//      <groupId>org.apache.hbase</groupId>
//      <artifactId>hbase-client</artifactId>
//      <version>2.0.2.3.1.0.6-1</version> <!-- Hortonworks Latest -->
//    </dependency>
//    <dependency>
//      <groupId>org.apache.hbase</groupId>
//      <artifactId>hbase-spark</artifactId>
//      <version>2.0.2.3.1.0.6-1</version> <!-- Hortonworks Latest -->
//    </dependency>
//    <dependency>
//      <groupId>com.hortonworks</groupId>
//      <artifactId>shc-core</artifactId>
//      <version>1.1.1-2.1-s_2.11</version> <!-- Hortonworks Latest -->
//    </dependency>
//  </dependencies>
//
//
//  package com.sparkbyexamples.spark.dataframe.hbase.hortonworks
//
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.datasources.hbase.HBaseTableCatalog
//
//object HBaseSparkRead {
//
//  def main(args: Array[String]): Unit = {
//
//    def catalog =
//      s"""{
//         |"table":{"namespace":"default", "name":"employee"},
//         |"rowkey":"key",
//         |"columns":{
//         |"key":{"cf":"rowkey", "col":"key", "type":"string"},
//         |"fName":{"cf":"person", "col":"firstName", "type":"string"},
//         |"lName":{"cf":"person", "col":"lastName", "type":"string"},
//         |"mName":{"cf":"person", "col":"middleName", "type":"string"},
//         |"addressLine":{"cf":"address", "col":"addressLine", "type":"string"},
//         |"city":{"cf":"address", "col":"city", "type":"string"},
//         |"state":{"cf":"address", "col":"state", "type":"string"},
//         |"zipCode":{"cf":"address", "col":"zipCode", "type":"string"}
//         |}
//         |}""".stripMargin
//
//    val sparkSession: SparkSession = SparkSession.builder()
//      .master("local")
//      .appName("Spark HBase DF")
//      .getOrCreate()
//
//    import sparkSession.implicits._
//
//    val hbaseDF = sparkSession.read
//      .options(Map(HBaseTableCatalog.tableCatalog -> catalog))
//      .format("org.apache.spark.sql.execution.datasources.hbase")
//      .load()
//
//    hbaseDF.printSchema()
//
//    hbaseDF.show(false)
//
//    hbaseDF.filter($"key" === "1" && $"state" === "FL")
//      .select("key", "fName", "lName")
//      .show()
//
//    //Create Temporary Table on DataFrame
//    hbaseDF.createOrReplaceTempView("employeeTable")
//
//    //Run SQL
//    sparkSession.sql("select * from employeeTable where fName = 'Amaya' ").show
//
//  }
//}
//
//   */
//
//}
