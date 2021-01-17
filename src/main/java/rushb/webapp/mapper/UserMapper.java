package rushb.webapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import rushb.webapp.model.Article;
import rushb.webapp.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select articleList from user where id = #{id}")
    List<Article> getAllArticle();

    @Update("update user set ...")
    void updateUser(long id);

    @Select("select * from user where id = #{id}")
    User findById(long id);

    @Select("select * from user where name = #{name}")
    User findByName(String name);



}
