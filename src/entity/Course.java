package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements SuperEntity{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String cName;
    private double cFee;
    private String duration;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<entity.Registration> registrations = new ArrayList<>();

    public Course(String code, String cName, double cFee, String duration) {
        this.code = code;
        this.cName = cName;
        this.cFee = cFee;
        this.duration = duration;
    }

    public Course() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public double getcFee() {
        return cFee;
    }

    public void setcFee(double cFee) {
        this.cFee = cFee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", cName='" + cName + '\'' +
                ", cFee=" + cFee +
                ", duration='" + duration + '\'' +
                '}';
    }

    public List<entity.Registration> getRegistrations() {
        return registrations;
    }
}
