package rushb.webapp.service;

import rushb.webapp.model.Article;
import rushb.webapp.model.User;

import java.util.List;

public interface UserService {
    List<Article> getAllArticle();

    void updateUser(long id);

    User findById(long id);

    User findByName(String name);

}
