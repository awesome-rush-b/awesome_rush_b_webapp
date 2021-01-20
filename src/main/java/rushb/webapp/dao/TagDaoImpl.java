package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.BlogTagMapper;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Tag;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class TagDaoImpl implements TagDao{
    private final TagMapper tagMapper;
    private final BlogTagMapper blogTagMapper;
    private TreeSet<Tag> tags;

    @Autowired
    public TagDaoImpl(TagMapper tagMapper, BlogTagMapper blogTagMapper) {
        this.tagMapper = tagMapper;
        this.blogTagMapper = blogTagMapper;
        this.tags = new TreeSet<>(Comparator.comparingInt(Tag::getCount));
    }

    @PostConstruct
    private void setTags(){
        this.tags.clear();
        this.tags.addAll(tagMapper.list());

    }
    @Override
    public Set<Tag> list() {
        return this.tags;
    }

    @Override
    public Tag findById(String id) {
        return tagMapper.findById(id);
    }

    @Override
    public Tag findByName(String name) {
        return tagMapper.findByName(name);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.update(tag);
        for(Tag temp : tags){
            if(temp.getTagId().equals(tag.getTagId())){
                temp.setCount(tag.getCount());
                temp.setName(tag.getName());
            }
        }
    }

    @Override
    public void save(Tag tag) {
        tagMapper.save(tag);
        tags.add(tag);
    }

    @Override
    public void delete(String tagId) {
        tagMapper.delete(tagId);
        tags.removeIf(tag -> tag.getTagId().equals(tagId));
    }

    @Override
    public List<Tag> mostNPopular(int n) {
        return null;
    }

    public void update(){
        while(tags.size() > 0 && tags.first().getCount() == 0)
            this.delete(tags.pollFirst().getTagId());
    }
}
