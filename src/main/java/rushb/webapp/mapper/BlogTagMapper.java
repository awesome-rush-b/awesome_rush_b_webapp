package rushb.webapp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogTagMapper {

    /**
     * List all tags attached to target blog
     * @param BlogId    the id of the target blog
     * @return  list of tags attached to target blog
     */
    @Select("select tagId from blogTag where blogId = #{blogId}")
    List<String> listByBlogId(String BlogId);

    /**
     * List all blogs attached with the target tag
     * @param TagId the id of the target tag
     * @return  list of blog attached with target tag
     */
    @Select("select blogId from blogTag where tagId = #{tagId}")
    List<String> listByTagId(String TagId);

    /**
     * save the Blog and Tag record to database
     * @param blogId    the Id of the blog to be saved
     * @param tagId     the Id of the tag to be saved
     */
    @Insert("insert into blogTag (blogId, tagId) value (#{blogId}, #{tagId})")
    void save(String blogId, String tagId);

    /**
     * delete all of the records related with target Blog from database
     * @param BlogId    the id of target Blog
     */
    @Delete("delete * from blogTag where blogId = #{blogId}")
    void deleteBlog(String BlogId);

    /**
     * delete all of the records related with target Tag from database
     * @param tagId     the id of the target tag
     */
    @Delete("delete * from blogTag where tagId = #{tagId}")
    void deleteTag(String tagId);
}
