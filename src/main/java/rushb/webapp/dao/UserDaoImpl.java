package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.UserMapper;
import rushb.webapp.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    UserMapper userMapper;

    @Autowired
    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public User findById(String userId) {
        return userMapper.findById(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void delete(String userId) {
        userMapper.deleteUser(userId);
    }
}
