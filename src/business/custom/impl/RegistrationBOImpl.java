package business.custom.impl;

import business.custom.RegistrationBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.RegistrationDAO;
import db.FactoryConfiguration;
import entity.Registration;
import model.CourseTM;
import model.RegistrationTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    private RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTRATION);

    @Override
    public List<RegistrationTM> getAllRegistrations() throws Exception {
        List<Registration> allRegistrations = null;
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            allRegistrations = registrationDAO.findAll();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

        List<RegistrationTM> registrations = new ArrayList<>();
        for (Registration registration : allRegistrations) {
            registrations.add(new RegistrationTM(registration.getRegNo(),
                    registration.getRegDate(),
                    registration.getRegFee(),
                    registration.getStudent_Id(),
                    registration.getCourse_Code()
            ));
        }
        return registrations;
    }

    @Override
    public void saveRegistration(String regNo, String regDate, double regFee, String student_Id, String course_Code)
            throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            registrationDAO.save(new Registration(regNo,regDate,regFee,student_Id,course_Code));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void deleteRegistration(String reg_Id) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            registrationDAO.delete(reg_Id);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void updateRegistration(String regDate, double regFee, String student_Id, String course_Code,String reg_Id)
            throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        registrationDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            registrationDAO.update(new Registration(reg_Id, regDate, regFee, student_Id, course_Code));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public String getNewRegistrationId() throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        registrationDAO.setSession(session);
        String lastRegNo = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            lastRegNo = registrationDAO.getLastRegNo();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

        if (lastRegNo == null) {
            return ("REG001");
        } else {
            int maxId = Integer.parseInt(lastRegNo.replace("REG", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "REG00" + maxId;
            } else if (maxId < 100) {
                id = "REG0" + maxId;
            } else {
                id = "REG" + maxId;
            }
            return id;
        }

    }
}
