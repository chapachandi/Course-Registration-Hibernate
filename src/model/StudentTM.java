package model;

public class StudentTM {
    private String id;
    private String sName;
    private String address;
    private String contact;
    private String dob;
    private String gender;


    public StudentTM(String id, String sName, String address, String contact, String dob, String gender) {
        this.id = id;
        this.sName = sName;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    public StudentTM() {
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
        return "StudentTM{" +
                "id='" + id + '\'' +
                ", sName='" + sName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
