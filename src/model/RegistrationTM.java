package model;


public class RegistrationTM {
    private String regNo;
    private String regDate;
    private double regFee;
    private String student_Id;
    private String course_Code;

    public RegistrationTM(String regNo, String regDate, double regFee, String student_Id, String course_Code) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student_Id = student_Id;
        this.course_Code = course_Code;
    }

    public RegistrationTM() {
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

    @Override
    public String toString() {
        return "RegistrationTM{" +
                "regNo=" + regNo +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", student_Id='" + student_Id + '\'' +
                ", course_Code='" + course_Code + '\'' +
                '}';
    }
}
