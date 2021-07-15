package entity;

import javax.persistence.*;

@Entity
public class Registration implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String regNo;
    private String regDate;
    private double regFee;
    private String student_Id;
    private String course_Code;
//    private RegistrationPK registrationPK;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_Id", referencedColumnName = "id", insertable = false , updatable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_Code",referencedColumnName = "code", insertable = false , updatable = false)
    private Course course;

//    public Registration(String student_Id, String course_Code, int regNo, String regDate, double regFee) {
//        this.registrationPK = new RegistrationPK(student_Id, course_Code);
//        this.regNo = regNo;
//        this.regDate = regDate;
//        this.regFee = regFee;
//    }
//    public Registration(RegistrationPK registrationPK, int regNo, String regDate, double regFee) {
//        this.registrationPK = registrationPK;
//        this.regNo = regNo;
//        this.regDate = regDate;
//        this.regFee = regFee;
//    }
//
//
//    public Registration(int regNo, String regDate, double regFee, RegistrationPK registrationPK, Student student, Course course) {
//        this.regNo = regNo;
//        this.regDate = regDate;
//        this.regFee = regFee;
//        this.registrationPK = registrationPK;
//        this.student = student;
//        this.course = course;
//    }


    public Registration(String regNo, String regDate, double regFee, String student_Id, String course_Code) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student_Id = student_Id;
        this.course_Code = course_Code;
    }

    public Registration(String regNo, String regDate, double regFee, String student_Id, String course_Code, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student_Id = student_Id;
        this.course_Code = course_Code;
        this.student = student;
        this.course = course;
    }

    public Registration() {
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public String getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(String student_Id) {
        this.student_Id = student_Id;
    }

    public String getCourse_Code() {
        return course_Code;
    }

    public void setCourse_Code(String course_Code) {
        this.course_Code = course_Code;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo=" + regNo +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", student_Id='" + student_Id + '\'' +
                ", course_Code='" + course_Code + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
