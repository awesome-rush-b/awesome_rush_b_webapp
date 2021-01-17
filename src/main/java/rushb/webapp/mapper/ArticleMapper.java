package rushb.webapp.mapper;


import org.apache.ibatis.annotations.*;
import rushb.webapp.model.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select * from article")
    List<Article> list();

    @Select("select * from article where author = #{name}")
    List<Article> listByAuthorName(String name);

    @Select("")
    List<Article> listByAuthorId(long AuthorId);

    @Select("")
    List<Article> listByTag(String tag);

    @Select("select * from article where id = #{id}")
    Article findById(long id);

    @Select("select * from article where name = #{name}")
    Article findByName(String name);

    @Delete("delete from article where id = #{id}")
    void deleteArticle(long id);

    //TODO
    @Update("update article set ...")
    void updateArticle(long id);

    @Insert("insert into article(article_id, name, create_date, modify_date, hashtag, status, author) values" +
            "(#{id}, #{name}, #{createDate}, #{modifyDate}, #{hashtag}, #{status}, #{author}")
//    void save(@Param("id") Long id, @Param("name") String name, @Param("createDate")Date createDate,
//              @Param("modifyDate") Date modifyDate, @Param("hashtag") List<String> hashtag,
//              @Param("status") String status, @Param("author") String author);
    void save(Article article);

}
