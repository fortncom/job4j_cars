package ru.job4j.cartrade.repository;

import org.hibernate.query.Query;
import ru.job4j.cartrade.model.Role;
import ru.job4j.cartrade.model.User;

public class UserRepositoryImpl implements UserRepository {

    private CommonRepository commonRepository = new CommonRepository();

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl instOf() {
        return UserRepositoryImpl.Lazy.INST;
    }

    private static final class Lazy {
        private static final UserRepositoryImpl INST = new UserRepositoryImpl();
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) commonRepository.execute(session -> {
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
        return commonRepository.execute(session -> session.get(Role.class, id));
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            commonRepository.create(user);
        } else {
            commonRepository.update(user);
        }
    }

    @Override
    public void save(Role role) {
        if (role.getId() == 0) {
            commonRepository.create(role);
        } else {
            commonRepository.update(role);
        }
    }
}
