package rushb.webapp.mapper;

import org.apache.ibatis.annotations.*;
import rushb.webapp.model.Subscriber;

import java.util.List;

@Mapper
public interface SubscriberMapper {

    /**
     * list all subscriber from database
     * @return  all of the subscriber
     */
    @Select("select * from subscriber")
    List<Subscriber> list();

    /**
     * list all subscriber following the target content creator
     * @param authorId  the id of the target content creator
     * @return  list of the subscriber following the target content creator
     */
    @Select("select * from subscriber where userId = #{authorId}")
    List<Subscriber> listByAuthorId(String authorId);

    /**
     * find the subscriber by its name
     * @param name  the name of the subscriber to be found
     * @return  the target subscriber to be found
     */
    @Select("select * from subscriber where name = #{name}")
    Subscriber findByName(String name);

    /**
     * find the subscriber by its id
     * @param subscriberId  the id of the subscriber to be found
     * @return  the target subscriber to be found
     */
    @Select("select * from subscriber where subscriberId = #{subscriberId}")
    Subscriber findById(String subscriberId);

    /**
     * find the subscriber by its email
     * @param email the email of the subscriber to be found
     * @return  the target subscriber to be found
     */
    @Select("select * from subscriber where email = #{email}")
    Subscriber findByEmail(String email);

    /**
     * update the subscriber
     * @param subscriber    the subscriber to be updated
     */
    @Update("update subscriber set userId = #{userId}, email = #{email}, " +
            "name = #{name} where subscriberId = #{subscriberId}")
    void updateSub(Subscriber subscriber);

    /**
     * create a subscriber
     * @param subscriber    the subscriber to be saved
     */
    @Insert("insert into subscriber(subscriberId, userId, email, name) " +
            "values(UUID(), #{userId}, #{email},#{name})")
    void save(Subscriber subscriber);

    /**
     * delete the subscriber
     * @param subscriberId    the subscriberId to be deleted
     */
    @Delete("delete from subscriber where subscriberId = #{subscriberId}")
    void delete(String subscriberId);

}
