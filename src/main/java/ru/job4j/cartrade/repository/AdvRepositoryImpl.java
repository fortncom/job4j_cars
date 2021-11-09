package ru.job4j.cartrade.repository;

import org.hibernate.query.Query;
import ru.job4j.cartrade.model.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class AdvRepositoryImpl extends CommonRepository implements AdvRepository {

    private AdvRepositoryImpl() {
    }

    public static AdvRepository instOf() {
        return Lazy.INST;
    }

    private static final class Lazy {
        private static final AdvRepository INST = new AdvRepositoryImpl();
    }

    @Override
    public List<Advertisement> findAllAds() {
        return execute(session -> {
            final Query query = session.createQuery(
                    "select distinct a from Advertisement a left join fetch a.photos p");
            return query.list();
        });
    }

    @Override
    public List<Advertisement> findAdsForLastDay() {
        Date date = Date.from(Instant.now().minus(Duration.ofDays(1)));
        return execute(session -> {
            final Query query = session.createQuery(
                    "select distinct a from Advertisement a left join fetch a.photos p "
                            + "where a.created > :date");
            query.setParameter("date", date);
            return query.list();
        });
    }

    @Override
    public List<Advertisement> findAdsWithPhotos() {
        return execute(session -> {
            final Query query = session.createQuery(
                    "select distinct a from Advertisement a left join fetch a.photos p "
                            + "where size(p) > 0");
            return query.list();
        });
    }

    @Override
    public List<Advertisement> findAdsByMark(Mark mark) {
        return execute(session -> {
            final Query query = session.createQuery(
                    "select distinct a from Advertisement a join fetch a.car c "
                            + "join fetch c.mark m "
                            + "join fetch c.model ml "
                            + "left join fetch a.photos p "
                            + "where m.name = :name");
            query.setParameter("name", mark.getName());
            return query.list();
        });
    }

    @Override
    public List<Advertisement> findAdsByFilter(Filter filter) {
        return execute(session -> {
            final Query query = session.createQuery(
                    "select distinct a from Advertisement a join fetch a.car c "
                            + "join fetch c.mark m "
                            + "join fetch c.model ml "
                            + "left join fetch a.photos p "
                            + "where m.id = :mId and ml.id = :mlId "
                            + "and a.price > :minPrice and a.price < :maxPrice");
            query.setParameter("mId", filter.getIdMark());
            query.setParameter("mlId", filter.getIdModel());
            query.setParameter("minPrice", filter.getPriceMin());
            query.setParameter("maxPrice", filter.getPriceMax());
            return query.list();
        });
    }

    @Override
    public Photo findPhotoById(int id) {
        return execute(session -> session.get(Photo.class, id));
    }

    @Override
    public Photo findPhotoByName(String name) {
        return (Photo) execute(session -> {
            final Query query = session.createQuery(
                    "select distinct p from Photo p "
                            + "where p.name = :name");
            query.setParameter("name", name);
            return query.uniqueResult();
        });
    }

    @Override
    public Advertisement findAdvById(int id) {
        return (Advertisement) execute(session -> {
            final Query query = session.createQuery(
                    "select distinct a from Advertisement a left join fetch a.photos p "
                            + "where a.id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }

    @Override
    public void save(Advertisement adv) {
        if (adv.getId() == 0) {
            create(adv);
        } else {
            update(adv);
        }
    }

    @Override
    public void save(Photo photo) {
        if (photo.getId() == 0) {
            create(photo);
        } else {
            update(photo);
        }
    }
}
