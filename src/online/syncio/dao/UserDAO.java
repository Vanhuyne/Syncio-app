package online.syncio.dao;
    
import online.syncio.model.User;

public interface UserDAO extends DAO<User> {
    public User authentication(String username, String password);
    public boolean checkEmail(String email); 
    public boolean checkUsername(String username); 
}

