package ru.job4j.cartrade.repository;

import org.hibernate.query.Query;
import ru.job4j.cartrade.model.Body;
import ru.job4j.cartrade.model.Car;
import ru.job4j.cartrade.model.Mark;
import ru.job4j.cartrade.model.Model;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private CommonRepository commonRepository = new CommonRepository();

    private CarRepositoryImpl() {
    }

    public static CarRepositoryImpl instOf() {
        return CarRepositoryImpl.Lazy.INST;
    }

    private static final class Lazy {
        private static final CarRepositoryImpl INST = new CarRepositoryImpl();
    }

    @Override
    public List<Car> findAllCars() {
        return commonRepository.execute(session -> {
            final Query query = session.createQuery(
                    "from Car");
            return query.list();
        });
    }

    @Override
    public Mark findMarkById(int id) {
        return commonRepository.execute(session -> session.get(Mark.class, id));
    }

    @Override
    public Body findBodyById(int id) {
        return commonRepository.execute(session -> session.get(Body.class, id));
    }

    @Override
    public Model findModelById(int id) {
        return commonRepository.execute(session -> session.get(Model.class, id));
    }

    @Override
    public Car findCarById(int id) {
        return (Car) commonRepository.execute(session -> {
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
        return (Car) commonRepository.execute(session -> {
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
    public void save(Car car) {
        if (car.getId() == 0) {
            commonRepository.create(car);
        } else {
            commonRepository.update(car);
        }
    }

    @Override
    public void save(Mark mark) {
        if (mark.getId() == 0) {
            commonRepository.create(mark);
        } else {
            commonRepository.update(mark);
        }
    }

    @Override
    public void save(Body body) {
        if (body.getId() == 0) {
            commonRepository.create(body);
        } else {
            commonRepository.update(body);
        }
    }

    @Override
    public void save(Model model) {
        if (model.getId() == 0) {
            commonRepository.create(model);
        } else {
            commonRepository.update(model);
        }
    }
}
