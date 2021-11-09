package ru.job4j.cartrade.repository;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cartrade.model.Role;
import ru.job4j.cartrade.model.User;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserRepositoryImplTest {

    private UserRepository repository;

    @Before
    public void setUp() throws SQLException {
        repository = UserRepositoryImpl.instOf();
    }

    @Test
    public void whenAddAndUpdateRole() {
        Role role = Role.of("admin");
        repository.save(role);
        role.setId(1);
        assertThat(repository.findRoleById(1), is(role));
        role.setName("manager");
        repository.save(role);
        assertThat(repository.findRoleById(1), is(role));
    }

    @Test
    public void whenAddAndUpdateUser() {
        Role role = Role.of("admin");
        repository.save(role);
        User user = User.of("Thomas", "tom1976@mail.ru", "12345", role);
        repository.save(user);
        role.setId(1);
        user.setId(1);
        assertThat(repository.findUserByEmail("tom1976@mail.ru"), is(user));
        role.setName("manager");
        user.setName("Alex");
        repository.save(user);
        assertThat(repository.findUserByEmail("tom1976@mail.ru"), is(user));
    }
}