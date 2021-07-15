package business.custom;

import business.SuperBO;
import model.StudentTM;

import java.util.List;

public interface StudentBO extends SuperBO {

    public List<StudentTM> getAllStudents() throws Exception;

    public void saveStudent(String id, String sName, String address,String contact, String dob,String gender)throws Exception;

    public void deleteStudent(String student_Id)throws Exception;

    public void updateStudent(String sName, String address,String contact, String dob,String gender, String student_Id)throws Exception;

    public String getNewStudentId()throws Exception;
}
