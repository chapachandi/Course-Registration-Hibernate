package business.custom;

import business.SuperBO;

public interface LoginBO extends SuperBO {
    public void saveUser(String username, String password)throws Exception;

    public void deleteCUser(String username)throws Exception;

    public void updateUser(String password, String username)throws Exception;
}
