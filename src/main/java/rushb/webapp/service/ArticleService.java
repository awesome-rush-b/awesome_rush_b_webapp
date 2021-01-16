package rushb.webapp.service;


import rushb.webapp.model.Article;

import java.util.List;


public interface ArticleService {
    List<Article> list();

    List<Article> listByAuthorName(String name);

    List<Article> listByAuthorId(long AuthorId);

    List<Article> listByTag(String tag);

    Article findById(long id);

    Article findByName(String name);

    void deleteArticle(long id);

    void updateArticle(long id);

    void save(Article article);
}
