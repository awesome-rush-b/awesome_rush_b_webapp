# awesome_rush_b_webapp

## DevLog

### 1/22/2021 - Zixiao

1. package related modification
   * Add ```config```, ```util```
     * ```config``` is used for ```@Configuration``` class
     * ```util``` is used for common tool class like jwt.
2. test related modification
    * Add ```SubscriberControllerTest```
3. dependency related modeification
    * Add ```junit``` dependency
4. **BUG**
    * ```mapper``` All insert methods need to be change. **Because the value of PK cannot be retrieved**
    Take ```UserMapper``` as an example:
        ```java
        // Bug code
        @Insert("insert into user(userId, username, password, email) values " +
               "(UUID(), #{username}, #{password}, #{email})")
        void save(User user);
        // Correct code: user @SelectKey to retrieve the PK
        // Reference: 
        // https://mybatis.org/mybatis-3/apidocs/org/apache/ibatis/annotations/SelectKey.html
        // https://www.cnblogs.com/loveLands/articles/10099768.html
        @SelectKey(statement = "SELECT UUID()", keyProperty = "userId", before = true, resultType = String.class)
        @Insert("insert into user(userId, username, password, email) values " +
             "(#{userId}, #{username}, #{password}, #{email})")
        void save(User user);
        ```
      
### 1/26/2021 - jonah

1. Implemented test classes for DAO classes, and user service
2. Minor change has been made for Dao impl and tag Mapper.
3. All test succeed for DAO classes and Service classes 

### 2/1/2021 - jonah
1. added 404 handler in controller for the resource not found