package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.RegistrationDAO;
import entity.Registration;

public class RegistrationDAOImpl extends CrudDAOImpl<Registration, String> implements RegistrationDAO {
    @Override
    public String getLastRegNo() throws Exception {
        return (String) session.createNativeQuery("SELECT regNo FROM Registration ORDER BY regNo DESC LIMIT 1")
                .uniqueResult();
    }
}
