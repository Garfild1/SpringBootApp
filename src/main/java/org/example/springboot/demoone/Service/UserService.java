package org.example.springboot.demoone.Service;

import org.example.springboot.demoone.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.springboot.demoone.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService (UserDao userDao) {
        this.userDao = userDao;
    }
    public void saveUser(User user) {
        userDao.save(user);
    }


    public void removeUser(Integer id) {
        userDao.delete(getUserById(id));
    }



    public void updateUser(User user) {
        userDao.saveAndFlush(user);
    }



    public User getUserById(Integer id) {
        return userDao.getOne(id.longValue());
    }



    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
