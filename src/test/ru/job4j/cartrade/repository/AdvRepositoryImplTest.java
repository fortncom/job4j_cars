package ru.job4j.cartrade.repository;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cartrade.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AdvRepositoryImplTest {

    private AdvRepository repository;

    @Before
    public void setUp() throws SQLException {
        repository = AdvRepositoryImpl.instOf();
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

    @Test
    public void whenAddAndUpdateMark() {
        Mark mark = Mark.of("Nissan");
        repository.save(mark);
        mark.setId(1);
        assertThat(repository.findMarkById(1), is(mark));
        mark.setName("Opel");
        repository.save(mark);
        assertThat(repository.findMarkById(1), is(mark));
    }

    @Test
    public void whenAddAndUpdateBody() {
        Body body = Body.of("Кроссовер");
        repository.save(body);
        body.setId(1);
        assertThat(repository.findBodyById(1), is(body));
        body.setName("Универсал");
        repository.save(body);
        assertThat(repository.findBodyById(1), is(body));
    }

    @Test
    public void whenAddAndUpdateModel() {
        Body body = Body.of("Кроссовер");
        Model model = Model.of("Juke", body);
        repository.save(model);
        body.setId(1);
        model.setId(1);
        assertThat(repository.findModelById(1), is(model));
        model.setName("Insignia");
        repository.save(model);
        assertThat(repository.findModelById(1), is(model));
    }

    @Test
    public void whenAddAndUpdateCar() {
        Mark mark = Mark.of("Nissan");
        repository.save(mark);
        Body body = Body.of("Кроссовер");
        Model model = Model.of("Juke", body);
        repository.save(model);
        Car car = Car.of(mark, model);
        repository.save(car);
        body.setId(1);
        mark.setId(1);
        model.setId(1);
        car.setId(1);
        assertThat(repository.findCarById(1), is(car));
        model.setName("Insignia");
        repository.save(car);
        assertThat(repository.findCarById(1), is(car));
    }

    @Test
    public void whenAddAndUpdatePhoto() {
        Photo photo = Photo.of("photo1324");
        repository.save(photo);
        photo.setId(1);
        assertThat(repository.findPhotoById(1), is(photo));
        photo.setName("photo345");
        repository.save(photo);
        assertThat(repository.findPhotoById(1), is(photo));
    }

    @Test
    public void whenAddAndUpdateAdvertisement() {
        Photo photo = Photo.of("photo1324");
        repository.save(photo);
        photo.setId(1);
        Mark mark = Mark.of("Nissan");
        repository.save(mark);
        mark.setId(1);
        Body body = Body.of("Кроссовер");
        repository.save(body);
        Model model = Model.of("Juke", body);
        repository.save(model);
        body.setId(1);
        model.setId(1);
        Car car = Car.of(mark, model);
        repository.save(car);
        car.setId(1);
        Role role = Role.of("admin");
        repository.save(role);
        role.setId(1);
        User user = User.of("Thomas", "tom1976@mail.ru", "12345", role);
        repository.save(user);
        user.setId(1);
        Advertisement adv = Advertisement.of(
                "description", false, 500000, List.of(photo), car, user);
        repository.save(adv);
        adv.setId(1);
        assertThat(repository.findAdvById(1), is(adv));
        adv.setPrice(1000000);
        adv.setCreated(new Date());
        repository.save(adv);
        assertThat(repository.findAdvById(1), is(adv));
        assertThat(repository.findAllAds(), is(List.of(adv)));
    }
}