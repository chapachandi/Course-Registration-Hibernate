package business.custom.impl;

import business.custom.StudentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.StudentDAO;
import db.FactoryConfiguration;
import entity.Student;
import model.StudentTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);


    @Override
    public List<StudentTM> getAllStudents() throws Exception {
        List<Student> allStudents = null;
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            allStudents = studentDAO.findAll();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

        List<StudentTM> students = new ArrayList<>();
        for (Student student : allStudents) {
            students.add(new StudentTM(student.getId(),
                    student.getsName(),
                    student.getAddress(),
                    student.getContact(),student.getDob(),student.getGender()));
        }
        return students;
    }

    @Override
    public void saveStudent(String id, String sName, String address, String contact, String dob, String gender)
            throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            studentDAO.save(new Student(id, sName, address,contact,dob,gender));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteStudent(String student_Id) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            studentDAO.delete(student_Id);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void updateStudent(String sName, String address, String contact, String dob, String gender,String student_Id)
            throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        studentDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            studentDAO.update(new Student(student_Id, sName, address,contact,dob,gender));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }
    }

    @Override
    public String getNewStudentId() throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        studentDAO.setSession(session);
        String lastStudentId = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            lastStudentId = studentDAO.getLastStudentId();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

        if (lastStudentId == null) {
            return "S001";
        } else {
            int maxId = Integer.parseInt(lastStudentId.replace("S", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "S00" + maxId;
            } else if (maxId < 100) {
                id = "S0" + maxId;
            } else {
                id = "S" + maxId;
            }
            return id;
        }

    }
}
