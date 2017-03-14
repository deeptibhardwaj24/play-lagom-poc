 Lagom Scala Word Count

This is a Scala Sbt project that demonstrates the legacy word count example using the Lagom Framework where we are hitting our rest service via Play application.
This is a microservices based Kafka Producer/Consumer application where one is the producer which produces data in Kafka and persist events in Cassandra Db. The other service consumes data from Kafka and gives the word count of the words.
Here, we are using embedded Kafka and Cassandra. You can use external ones as well. For that you just need to uncomment the last 4 lines of the build.sbt and install Kafka and Cassandra on your machine.You need to start Zookeeper server, Kafka server and Cassandra before starting the application as well.

