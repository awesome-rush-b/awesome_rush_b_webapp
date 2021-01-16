package rushb.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private String email;
    private String password;
    private List<Article> articleList;
    private int id;
}
