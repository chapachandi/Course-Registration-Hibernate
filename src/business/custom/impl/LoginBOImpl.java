package business.custom.impl;

import business.custom.LoginBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.LoginDAO;
import db.FactoryConfiguration;
import entity.Course;
import entity.Login;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginBOImpl implements LoginBO {

    private LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOType.LOGIN);

    @Override
    public void saveUser(String username, String password) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        loginDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            loginDAO.save(new Login(username,password));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void deleteCUser(String username) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        loginDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            loginDAO.delete(username);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }

    @Override
    public void updateUser(String password, String username) throws Exception {
        Session session = FactoryConfiguration.getSessionFactory().openSession();
        loginDAO.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            loginDAO.update(new Login(username,password));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw t;
        }finally {
            session.close();
        }

    }
}
