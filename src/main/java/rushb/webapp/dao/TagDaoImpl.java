package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.TagMapper;
import rushb.webapp.model.Tag;

import java.util.*;

@Repository
public class TagDaoImpl implements TagDao{
    private final TagMapper tagMapper;
    private final TreeSet<Tag> tags;

    @Autowired
    public TagDaoImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
        this.tags = new TreeSet<>(Comparator.comparingInt(Tag::getCount));
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
        //if current tag is not used, then delete it
        if(tag.getCount() == 0) {
            tagMapper.delete(tag.getTagId());
            tags.remove(tag);
        }
        //else update the tag information in local and database
        else{
            tagMapper.update(tag);
            for(Tag temp : tags){
                if(temp.getTagId().equals(tag.getTagId())){
                    temp.setCount(tag.getCount());
                    temp.setName(tag.getName());
                    break;
                }
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
        List<Tag> tagList= new ArrayList<>();
        Iterator<Tag> iterator = tags.descendingIterator();
        while(iterator.hasNext() && n>0){
            tagList.add(iterator.next());
        }
        return tagList;
    }

    @Override
    public void detachTag(Tag tag) {
        Tag oldTag = tagMapper.findById(tag.getTagId());
        oldTag.setCount(oldTag.getCount()-1);
        this.updateTag(oldTag);
    }
}
