package rushb.webapp.mapper;


import org.apache.ibatis.annotations.*;
import rushb.webapp.model.Blog;

import java.util.List;

@Mapper
public interface BlogMapper {

    /**
     * List all of the blog from database
     * @return all of the blogs
     */
    @Select("select * from blog")
    List<Blog> list();

    /**
     * list all of the blog from the same author
     * @param authorId the id of the target author
     * @return  list all of the blog from the target author
     */
    @Select("select * from blog where authorId = #{authorId}")
    List<Blog> listByAuthorId(String authorId);

    /**
     * find blog by id
     * @param blogId the id of the blog to be found
     * @return  the target blog
     */
    @Select("select * from blog where blogId = #{blogId}")
    Blog findById(String blogId);

    /**
     * find blog by title
     * @param title the title of the blog to be found
     * @return  the target blog
     */
    @Select("select * from blog where name = #{name}")
    Blog findByTitle(String title);

    //TODO to set the CreateDate and ModifyDate logic in Database side
    /**
     * update the target blog
     * @param blog the blog to be updated
     */
    @Update("update blog set blogId = #{blogId}, authorId = #{authorId}, title = #{title}, content = #{content}, status = #{status}")
    void updateBlog(Blog blog);

    /**
     * create a blog
     * @param blog  the blog to be created
     */
    @Insert("insert into blog (blogId, title, createDate, modifyDate, content, status, authorId) values" +
            "(UUID(), #{title}, #{createDate}, #{modifyDate}, #{content}, #{status}, #{authorId})")
//    void save(@Param("id") Long id, @Param("name") String name, @Param("createDate")Date createDate,
//              @Param("modifyDate") Date modifyDate, @Param("hashtag") List<String> hashtag,
//              @Param("status") String status, @Param("author") String author);
    void save(Blog blog);

    /**
     * delete a blog
     * @param blogId    the id of the blog to be deleted
     */
    @Delete("delete from blog where blogId = #{blogId}")
    void deleteArticle(String blogId);





}
