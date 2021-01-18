package rushb.webapp.service;

import rushb.webapp.model.User;

import java.util.List;

public interface UserService {
    List<User> list();

    User findByName(String username);

    User findById(String userId);

    User findByEmail(String email);

    void updateUser(User user);

    void save(User user);

    void delete(String userId);

}
