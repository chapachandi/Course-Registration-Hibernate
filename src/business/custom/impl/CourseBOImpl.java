package business.custom.impl;

import business.custom.CourseBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CourseDAO;
import db.FactoryConfiguration;
import entity.Course;
import model.CourseTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    private CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);

    @Override
    public List<CourseTM> getAllCourses() throws Exception {
        List<Course> allCourses = null;
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            allCourses = courseDAO.findAll();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

        List<CourseTM> courses = new ArrayList<>();
        for (Course course : allCourses) {
            courses.add(new CourseTM(course.getCode(),
                    course.getcName(),
                    course.getDuration(),
                    course.getcFee()
                    ));
        }
        return courses;
    }

    @Override
    public void saveCourse(String code, String cName, double cFee, String duration) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            courseDAO.save(new Course(code,cName,cFee,duration));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void deleteCourse(String course_code) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            courseDAO.delete(course_code);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void updateCourse(String cName, double cFee, String duration, String course_code) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        courseDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            courseDAO.update(new Course(course_code,cName,cFee,duration));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public String getNewProgramCodeNo() throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        courseDAO.setSession(session);
        String lastProgramCodeNo = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            lastProgramCodeNo = courseDAO.getLastProgramCodeNo();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

        if (lastProgramCodeNo == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastProgramCodeNo.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }

    }
}
