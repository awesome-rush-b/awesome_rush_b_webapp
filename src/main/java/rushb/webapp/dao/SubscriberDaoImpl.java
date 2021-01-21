package rushb.webapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rushb.webapp.mapper.SubscriberMapper;
import rushb.webapp.model.Subscriber;

import java.util.List;

@Repository
public class SubscriberDaoImpl implements SubscriberDao{
    private final SubscriberMapper subscriberMapper;

    @Autowired
    public SubscriberDaoImpl(SubscriberMapper subscriberMapper) {
        this.subscriberMapper = subscriberMapper;
    }

    @Override
    public List<Subscriber> list() {
        return subscriberMapper.list();
    }

    @Override
    public List<Subscriber> listByAuthorId(String authorId) {
        return subscriberMapper.listByAuthorId(authorId);
    }

    @Override
    public Subscriber findByName(String name) {
        return subscriberMapper.findByName(name);
    }

    @Override
    public Subscriber findById(String id) {
        return subscriberMapper.findById(id);
    }

    @Override
    public Subscriber findByEmail(String email) {
        return subscriberMapper.findByEmail(email);
    }

    @Override
    public void updateSub(Subscriber subscriber) {
        subscriberMapper.updateSub(subscriber);
    }

    @Override
    public void save(Subscriber subscriber) {
        subscriberMapper.save(subscriber);
    }

    @Override
    public void delete(String subscriberId) {
        subscriberMapper.delete(subscriberId);
    }
}
