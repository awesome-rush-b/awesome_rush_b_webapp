package rushb.webapp.dao;

import rushb.webapp.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagDao {

    Set<Tag> list();

    Tag findById(String id);

    Tag findByName(String name);

    void updateTag(Tag tag);

    void save(Tag tag);

    void delete(String tagId);

    List<Tag> mostNPopular(int n);

    void detachTag(Tag tag);
}
