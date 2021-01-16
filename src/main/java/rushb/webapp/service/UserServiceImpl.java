package rushb.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rushb.webapp.mapper.UserMapper;
import rushb.webapp.model.Article;
import rushb.webapp.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<Article> getAllArticle() {
        return userMapper.getAllArticle();
    }

    @Override
    public void updateUser(long id) {
        userMapper.updateUser(id);
    }

    @Override
    public User findById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
