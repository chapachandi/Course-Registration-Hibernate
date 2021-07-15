package entity;

import javax.persistence.Embeddable;

@Embeddable
public class RegistrationPK implements SuperEntity{
    private String student_Id;
    private String course_Code;

    public RegistrationPK(String student_Id, String course_Code) {
        this.student_Id = student_Id;
        this.course_Code = course_Code;
    }

    public RegistrationPK() {
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
        return "RegistrationPK{" +
                "student_Id='" + student_Id + '\'' +
                ", course_Code='" + course_Code + '\'' +
                '}';
    }
}
