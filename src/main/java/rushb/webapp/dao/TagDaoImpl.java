package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.BlogTagMapper;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Tag;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class TagDaoImpl implements TagDao{
    private final TagMapper tagMapper;
    private final BlogTagMapper blogTagMapper;
    private Set<Tag> tags;

    @Autowired
    public TagDaoImpl(TagMapper tagMapper, BlogTagMapper blogTagMapper) {
        this.tagMapper = tagMapper;
        this.blogTagMapper = blogTagMapper;
        this.tags = new TreeSet<>(Comparator.comparingInt(Tag::getCount));
    }

    @PostConstruct
    private void setTags(){
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
}
