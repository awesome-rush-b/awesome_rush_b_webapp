package rushb.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rushb.webapp.mapper.ArticleMapper;
import rushb.webapp.mapper.UserMapper;
import rushb.webapp.model.Article;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleMapper articleMapper;
    UserMapper userMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, UserMapper userMapper) {
        this.articleMapper = articleMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<Article> list() {
        return articleMapper.list();
    }

    @Override
    public List<Article> listByAuthorName(String name) {
        return articleMapper.listByAuthorName(name);
    }

    @Override
    public List<Article> listByAuthorId(long AuthorId) {
        return articleMapper.listByAuthorId(AuthorId);
    }

    @Override
    public List<Article> listByTag(String tag) {
        return articleMapper.listByTag(tag);
    }

    @Override
    public Article findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public Article findByName(String name) {
        return articleMapper.findByName(name);
    }

    @Override
    public void deleteArticle(long id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public void updateArticle(long id) {
        articleMapper.updateArticle(id);
    }

    @Override
    public void save(Article article) {
        //TODO to make sure if adding/deleting article to the author need to be done in frontend or backend
        articleMapper.save(article);
    }
}
