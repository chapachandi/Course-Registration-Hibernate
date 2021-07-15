package dao.custom;

import dao.CrudDAO;
import entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration,String> {
    String getLastRegNo() throws Exception;
}
