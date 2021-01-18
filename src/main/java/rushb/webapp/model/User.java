package rushb.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private String email;
    private String password;
    private List<Blog> blogList;
    private String userId;

    public void deleteArticle(Blog blog){
        blogList.remove(blog);
    }

    public void addArticle(Blog blog){
        blogList.add(blog);
    }
}
