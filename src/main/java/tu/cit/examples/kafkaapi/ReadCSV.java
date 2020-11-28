package tu.cit.examples.kafkaapi;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import tu.cit.examples.kafkaapi.schemas.student;

import java.io.FileReader;
import java.util.List;

public class ReadCSV {
    public String csvFileName = "data/student1.csv";
    public List stdlist;
    public List ReadCSVFile()  {

        try {
           CSVReader csvReader = new CSVReader(new FileReader(csvFileName));

            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(student.class)
                    .withIgnoreLeadingWhiteSpace(true).build();

            //This method is not recommended for large CSV File
            stdlist = csvToBean.parse();
//            for (Object std1 : stdlist) {
//                student std = (student) std1;
//                System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject() + " : " + std.getMarks());
//                //System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject());
//            }
            csvReader.close();
        }catch(Exception FileNotFoundException){
            //e.printStackTrace();
            System.out.println("File is not available...");
        }
        //This is good for large csv file
//        for(student std : (Iterable<student>) csvToBean){
//            System.out.println(std.getStudentid() + " : " + std.getName() + " : " + std.getDept() + " : " + std.getSubject() + " : " + std.getMarks());
//        }

        return stdlist;
    }
}
