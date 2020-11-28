package tu.cit.examples.kafkaapi;


import tu.cit.examples.kafkaapi.serde.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tu.cit.examples.kafkaapi.schemas.student;
import java.util.List;
import java.util.Properties;


public class ProducerReadCSV {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args)  {

        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"my-app-readcsv");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        logger.info("Producer has been created...Start sending Student Record ");

        KafkaProducer<String,student> producer = new KafkaProducer<String,student>(props);

        ReadCSV readCSV = new ReadCSV();
        List studentList = readCSV.ReadCSVFile(); //It will return the student list
        for (Object studentObject : studentList) {
            student stdobject = (student) studentObject;
            producer.send(new ProducerRecord<String, student>("student",stdobject.getDept(),stdobject));
         }
        logger.info("Producer has sent all employee records successfully...");
        producer.close();
    }


}
