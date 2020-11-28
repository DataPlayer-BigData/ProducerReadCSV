#kafka-topics.sh --create --zookeeper localhost:2181 --topic TextLinesTopic --replication-factor 1 --partitions 3

#kafka-topics.sh --create --zookeeper localhost:2181 --topic streams-wordcount-output --replication-factor 1 --partitions 3

kafka-topics.sh --create --zookeeper localhost:2181 --topic student --replication-factor 3 --partitions 3
