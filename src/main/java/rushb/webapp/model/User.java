package rushb.webapp.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private String email;
    private String password;
    private List<Article> articleList;
    private long id;

    public void deleteArticle(Article article){
        articleList.remove(article);
    }

    public void addArticle(Article article){
        articleList.add(article);
    }
}
