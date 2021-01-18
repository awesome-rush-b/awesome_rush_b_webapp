package rushb.webapp.mapper;

import org.apache.ibatis.annotations.*;
import rushb.webapp.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * select all of the user form db
     * @return list of all of the user
     */
    @Select("select * from user")
    List<User> list();

    /**
     * find user by Id
     * @param id the id of the user to be found
     * @return the target user
     */
    @Select("select * from user where userId = #{id}")
    User findById(String id);

    /**
     * find user by username
     * @param name  the username of the user to be found
     * @return  the target user
     */
    @Select("select * from user where username = #{name}")
    User findByName(String name);

    /**
     * find user by email
     * @param email the email of the user to be found
     * @return  the target user
     */
    @Select("select * from user where email = #{email}")
    User findByEmail(String email);

    /**
     * update the target user
     * @param user the user to be updated
     */
    @Update("update user set username = #{username}, email = #{email}, " +
            "password = #{password} where userId = #{userId}")
    void updateUser(User user);

    /**
     * Save the user into database
     * @param user the user to be registered
     */
    @Insert("insert into user(userId, username, password, email) values " +
            "(UUID(), #{username}, #{password}, #{email})")
    void save(User user);

    /**
     * delete the user in database
     * @param userId the userid of the user to be deleted
     */
    @Delete("delete from user where userId = #{userId}")
    void deleteUser(String userId);




}
