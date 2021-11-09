package ru.job4j.cartrade.repository;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cartrade.model.Body;
import ru.job4j.cartrade.model.Car;
import ru.job4j.cartrade.model.Mark;
import ru.job4j.cartrade.model.Model;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CarRepositoryImplTest {

    private CarRepository repository;

    @Before
    public void setUp() throws SQLException {
        repository = CarRepositoryImpl.instOf();
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
}