package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements SuperEntity{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(name = "sName")
    private String sName;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "dob")
    private String dob;
    @Column(name = "gender")
    private String gender;
    @OneToMany(mappedBy = "student", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Registration> registration = new ArrayList<>();

    public Student(String id, String sName, String address, String contact, String dob, String gender) {
        this.id = id;
        this.sName = sName;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", sName='" + sName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public List<Registration> getRegistration() {

        return registration;
    }
}
