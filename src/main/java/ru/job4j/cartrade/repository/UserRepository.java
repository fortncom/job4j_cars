package ru.job4j.cartrade.repository;

import ru.job4j.cartrade.model.Role;
import ru.job4j.cartrade.model.User;

public interface UserRepository {

    User findUserByEmail(String email);

    Role findRoleById(int id);

    void save(User user);

    void save(Role role);
}
