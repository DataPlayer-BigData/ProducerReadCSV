package tu.cit.examples.kafkaapi.schemas;

import com.opencsv.bean.CsvBindByName;

public class student {
    @CsvBindByName
    public int studentid;

    @CsvBindByName
    public String name;

    @CsvBindByName(column="department")
    public String dept;

    @CsvBindByName
    public String subject;

    @CsvBindByName
    public double marks;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}
