package rushb.webapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import rushb.webapp.model.Article;
import rushb.webapp.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Article> getAllArticle();

    void updateUser(long id);

    User findById(long id);

    User findByName(String name);



}
