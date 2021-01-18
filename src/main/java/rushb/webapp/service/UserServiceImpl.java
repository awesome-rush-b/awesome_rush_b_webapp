package rushb.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rushb.webapp.dao.UserDao;
import rushb.webapp.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public User findById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(String userId) {
        userDao.delete(userId);
    }
}
