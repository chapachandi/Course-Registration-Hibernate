package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.CourseDAO;
import entity.Course;

public class CourseDAOImpl extends CrudDAOImpl<Course, String> implements CourseDAO {
    @Override
    public String getLastProgramCodeNo() throws Exception {
        return (String) session.createNativeQuery("SELECT code FROM Course ORDER BY code DESC LIMIT 1").uniqueResult();
    }
}
