package dao;

import java.util.List;

import model.User;

public interface UserDAO {
    String addUser(User user);
//    void updateUser(User user);
//    void deleteUser(int userId);
//    User getUserById(int userId);
    List<User> getAllUsers();
}
