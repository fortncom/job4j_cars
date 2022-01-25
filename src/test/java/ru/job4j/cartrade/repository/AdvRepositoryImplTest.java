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

    private AdvRepository advRepository;

    @Before
    public void setUp() throws SQLException {
        advRepository = AdvRepositoryImpl.instOf();
    }

    @Test
    public void whenAddAndUpdatePhoto() {
        Photo photo = Photo.of("photo1324");
        advRepository.save(photo);
        photo.setId(1);
        assertThat(advRepository.findPhotoById(1), is(photo));
        photo.setName("photo345");
        advRepository.save(photo);
        assertThat(advRepository.findPhotoById(1), is(photo));
    }

    @Test
    public void whenAddAndUpdateAdvertisement() {
        CarRepository carRepository = CarRepositoryImpl.instOf();
        UserRepository userRepository = UserRepositoryImpl.instOf();
        Photo photo = Photo.of("photo1324");
        advRepository.save(photo);
        photo.setId(1);
        Mark mark = Mark.of("Nissan");
        carRepository.save(mark);
        mark.setId(1);
        Body body = Body.of("Кроссовер");
        carRepository.save(body);
        Model model = Model.of("Juke", body);
        carRepository.save(model);
        body.setId(1);
        model.setId(1);
        Car car = Car.of(mark, model);
        carRepository.save(car);
        car.setId(1);
        Role role = Role.of("admin");
        userRepository.save(role);
        role.setId(1);
        User user = User.of("Thomas", "tom1976@mail.ru", "12345", role);
        userRepository.save(user);
        user.setId(1);
        Advertisement adv = Advertisement.of(
                "description", false, 500000, List.of(photo), car, user);
        advRepository.save(adv);
        adv.setId(1);
        assertThat(advRepository.findAdvById(1), is(adv));
        adv.setPrice(1000000);
        adv.setCreated(new Date());
        advRepository.save(adv);
        assertThat(advRepository.findAdvById(1), is(adv));
        assertThat(advRepository.findAllAds(), is(List.of(adv)));
    }
}