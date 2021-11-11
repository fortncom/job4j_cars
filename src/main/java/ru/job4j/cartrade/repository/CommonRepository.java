package ru.job4j.cartrade.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class CommonRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CommonRepository.class.getName());

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();

    private static final SessionFactory SF = new MetadataSources(REGISTRY).buildMetadata()
            .buildSessionFactory();

    protected <T> T create(T t) {
        return execute(session -> {
            session.save(t);
            return t;
        });
    }

    protected <T> void update(T t) {
        execute(session -> {
            session.update(t);
            return t;
        });
    }

    protected <T> T execute(final Function<Session, T> command) {
        final Session session = SF.openSession();
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
