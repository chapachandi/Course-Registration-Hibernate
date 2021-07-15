package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.StudentDAO;
import entity.Student;

public class StudentDAOImpl extends CrudDAOImpl<Student, String> implements StudentDAO {

    @Override
    public String getLastStudentId() throws Exception {
        return (String) session.createNativeQuery("SELECT id FROM Student ORDER BY id DESC LIMIT 1").uniqueResult();
    }
}
