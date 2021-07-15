package business.custom;

import business.SuperBO;
import model.CourseTM;
import model.StudentTM;

import java.util.List;

public interface CourseBO extends SuperBO {
    public List<CourseTM> getAllCourses() throws Exception;

    public void saveCourse(String code, String cName, double cFee, String duration)throws Exception;

    public void deleteCourse(String course_code)throws Exception;

    public void updateCourse(String cName, double cFee, String duration, String course_code)throws Exception;

    public String getNewProgramCodeNo()throws Exception;
}
