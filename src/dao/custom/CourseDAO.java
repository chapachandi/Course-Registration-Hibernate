package dao.custom;

import dao.CrudDAO;
import entity.Course;
import entity.Student;

public interface CourseDAO extends CrudDAO<Course,String> {
    String getLastProgramCodeNo() throws Exception;
}
