package rushb.webapp.service;

import rushb.webapp.model.Subscriber;

import java.util.List;

public interface SubscriberService {
    List<Subscriber> list();

    List<Subscriber> listByAuthorId(String authorId);

    Subscriber findByName(String name);

    Subscriber findById(String id);

    Subscriber findByEmail(String email);

    void updateSub(Subscriber subscriber);

    void save(Subscriber subscriber);

    void delete(String subscriberId);
}
