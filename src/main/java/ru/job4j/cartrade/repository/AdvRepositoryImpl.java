package ru.job4j.cartrade.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cartrade.model.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdvRepositoryImpl implements AdvRepository {

    private static final Logger LOG = LoggerFactory.getLogger(AdvRepositoryImpl.class.getName());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata()
            .buildSessionFactory();

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
    public List<Car> findAllCars() {
        return execute(session -> {
            final Query query = session.createQuery(
                    "from Car");
            return query.list();
        });
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) execute(session -> {
            final Query query = session.createQuery(
                    "select distinct u from User u "
                            + "left join fetch u.role "
                            + "where u.email = :email");
            query.setParameter("email", email);
            return query.uniqueResult();
        });
    }

    @Override
    public Role findRoleById(int id) {
        return execute(session -> session.get(Role.class, id));
    }

    @Override
    public Mark findMarkById(int id) {
        return execute(session -> session.get(Mark.class, id));
    }

    @Override
    public Body findBodyById(int id) {
        return execute(session -> session.get(Body.class, id));
    }

    @Override
    public Model findModelById(int id) {
        return execute(session -> session.get(Model.class, id));
    }

    @Override
    public Car findCarById(int id) {
        return (Car) execute(session -> {
            final Query query = session.createQuery(
                    "select distinct c from Car c "
                            + "join fetch c.mark "
                            + "join fetch c.model "
                            + "where c.id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }

    @Override
    public Car findCarByMarkAndModel(int idMark, int idModel) {
        return (Car) execute(session -> {
            final Query query = session.createQuery(
                    "select distinct c from Car c "
                            + "join c.mark mk join c.model ml "
                            + "where mk.id = :idMark and ml.id= :idModel");
            query.setParameter("idMark", idMark);
            query.setParameter("idModel", idModel);
            return query.uniqueResult();
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
    public void save(User user) {
        if (user.getId() == 0) {
            create(user);
        } else {
            update(user);
        }
    }

    @Override
    public void save(Role role) {
        if (role.getId() == 0) {
            create(role);
        } else {
            update(role);
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

    @Override
    public void save(Car car) {
        if (car.getId() == 0) {
            create(car);
        } else {
            update(car);
        }
    }

    @Override
    public void save(Mark mark) {
        if (mark.getId() == 0) {
            create(mark);
        } else {
            update(mark);
        }
    }

    @Override
    public void save(Body body) {
        if (body.getId() == 0) {
            create(body);
        } else {
            update(body);
        }
    }

    @Override
    public void save(Model model) {
        if (model.getId() == 0) {
            create(model);
        } else {
            update(model);
        }
    }

    private <T> T create(T t) {
        return execute(session -> {
            session.save(t);
            return t;
        });
    }

    private <T> void update(T t) {
        execute(session -> {
            session.update(t);
            return t;
        });
    }

    private <T> T execute(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }
}
