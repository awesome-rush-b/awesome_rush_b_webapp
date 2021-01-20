package rushb.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rushb.webapp.dao.SubscriberDao;
import rushb.webapp.model.Subscriber;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService{
    private SubscriberDao subscriberDao;

    @Autowired
    public SubscriberServiceImpl(SubscriberDao subscriberDao) {
        this.subscriberDao = subscriberDao;
    }

    @Override
    public List<Subscriber> list() {
        return subscriberDao.list();
    }

    @Override
    public List<Subscriber> listByAuthorId(String authorId) {
        return subscriberDao.listByAuthorId(authorId);
    }

    @Override
    public Subscriber findByName(String name) {
        return subscriberDao.findByName(name);
    }

    @Override
    public Subscriber findById(String id) {
        return subscriberDao.findById(id);
    }

    @Override
    public Subscriber findByEmail(String email) {
        return subscriberDao.findByEmail(email);
    }

    @Override
    public void updateSub(Subscriber subscriber) {
        subscriberDao.updateSub(subscriber);
    }

    @Override
    public void save(Subscriber subscriber) {
        subscriberDao.save(subscriber);
    }

    @Override
    public void delete(String subscriberId) {
        subscriberDao.delete(subscriberId);
    }
}
