package model;

public class CourseTM {
    private String code;
    private String cName;
    private String duration;
    private double cFee;

    public CourseTM(String code, String cName, String duration, double cFee) {
        this.code = code;
        this.cName = cName;
        this.duration = duration;
        this.cFee = cFee;
    }

    public CourseTM() {
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

    @Override
    public String toString() {
        return "CourseTM{" +
                "code='" + code + '\'' +
                ", cName='" + cName + '\'' +
                ", duration='" + duration + '\'' +
                ", cFee=" + cFee +
                '}';
    }
}
