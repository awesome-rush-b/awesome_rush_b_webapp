package rushb.webapp.mapper;

import org.apache.ibatis.annotations.*;
import rushb.webapp.model.Tag;

import java.util.Set;

@Mapper
public interface TagMapper {

    /**
     * list all tags from database
     * @return list of al tags from database
     */
    @Select("select * from tag")
    Set<Tag> list();


    Set<Tag> listTopN(int number);

    /**
     * find the tag by id
     * @param id    the id of the target tag
     * @return  the target tag
     */
    @Select("select * from tag where tagId = #{id}")
    Tag findById(String id);

    /**
     * find the tag by name
     * @param name  the name of the target tag
     * @return  the target tag
     */
    @Select("select * from tag where name = #{name}")
    Tag findByName(String name);

    /**
     * update target tag
     * @param tag   the tag to be updated
     */
    @Update("update tag set name = #{name}, count = #{count} where tagId = #{tagId}")
    void update(Tag tag);

    /**
     * save target tag
     * @param tag   the tag to be saved
     */
    @Insert("insert into tag (tagId, name, count) value (UUID(), #{name}, #{count})")
    void save(Tag tag);

    /**
     * delete the target tag
     * @param tagId the id of the tag to be deleted
     */
    @Delete("delete from tag where tagId = #{tagId}")
    void delete(String tagId);
}
