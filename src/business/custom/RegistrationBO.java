package business.custom;

import business.SuperBO;
import model.RegistrationTM;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    public List<RegistrationTM> getAllRegistrations() throws Exception;

    public void saveRegistration(String regNo, String regDate, double regFee,String student_Id, String course_Code)throws Exception;

    public void deleteRegistration(String reg_Id)throws Exception;

    public void updateRegistration(String regDate, double regFee,String student_Id, String course_Code, String reg_Id)throws Exception;

    public String getNewRegistrationId()throws Exception;
}
