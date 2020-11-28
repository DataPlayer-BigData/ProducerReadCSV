package tu.cit.examples.kafkaapi;

//import com.fasterxml.jackson.databind.JsonSerializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tu.cit.examples.kafkaapi.schemas.student;
import tu.cit.examples.kafkaapi.serde.JsonSerializer;

import java.util.List;
import java.util.Properties;

//http://attacomsian.com/blog/read-write-csv-files-opencsv
public class ProducerReadCSVOriginal {

    //private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args)  {

//        try {
//            String csvFileName = "data/student.csv";
//            CSVReader csvReader = new CSVReader(new FileReader(csvFileName));
//
//            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
//                    .withType(student.class)
//                    .withIgnoreLeadingWhiteSpace(true).build();
//
//            //This method is not recommended for large CSV File
//            List stdlist = csvToBean.parse();
//            for (Object std1 : stdlist) {
//                student std = (student) std1;
//                System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject() + " : " + std.getMarks());
//                //System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject());
//            }
//            csvReader.close();
//        }catch(Exception FileNotFoundException){
//            //e.printStackTrace();
//            System.out.println("File is not available...");
//        }
        //This is good for large csv file
//        for(student std : (Iterable<student>) csvToBean){
//            System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject() + " : " + std.getMarks());
//        }



//        ReadCSV readCSV = new ReadCSV();
//        List studentList = readCSV.ReadCSVFile(); //It will return the student list
//        for (Object std1 : studentList) {
//            student std = (student) std1;
//            System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject() + " : " + std.getMarks());
//            //System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject());
//        }

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
