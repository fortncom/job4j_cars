package ru.job4j.cartrade.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cartrade.model.Advertisement;
import ru.job4j.cartrade.model.Mark;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepositoryImpl implements AdRepository {

    private static final Logger LOG = LoggerFactory.getLogger(AdRepositoryImpl.class.getName());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata()
            .buildSessionFactory();

    private AdRepositoryImpl() {
    }

    public static AdRepository instOf() {
        return Lazy.INST;
    }

    @Override
    public List<Advertisement> findAdsForLastDay() {
        Date date = Date.from(Instant.now().minus(Duration.ofDays(1)));
        return execute(session -> {
            final Query query = session.createQuery(
                    "from Advertisement a left join fetch a.photos p "
                            + "where a.created > :date");
            query.setParameter("date", date);
            return query.list();
        });
    }

    @Override
    public List<Advertisement> findAdsWithPhotos() {
        return execute(session -> {
            final Query query = session.createQuery(
                    "from Advertisement a left join fetch a.photos p "
                            + "where size(p) > 0");
            return query.list();
        });
    }

    @Override
    public List<Advertisement> findAdsByMark(Mark mark) {
        return execute(session -> {
            final Query query = session.createQuery(
                    "from Advertisement a join fetch a.car c "
                            + "join fetch c.body "
                            + "join fetch c.mark m "
                            + "join fetch a.user u "
                            + "join fetch u.role r "
                            + "left join fetch a.photos p "
                            + "where m.name = :name");
            query.setParameter("name", mark.getName());
            return query.list();
        });
    }

    private static final class Lazy {
        private static final AdRepository INST = new AdRepositoryImpl();
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
