package ru.job4j.cartrade.repository;

import ru.job4j.cartrade.model.Body;
import ru.job4j.cartrade.model.Car;
import ru.job4j.cartrade.model.Mark;
import ru.job4j.cartrade.model.Model;

import java.util.List;

public interface CarRepository {

    List<Car> findAllCars();

    Mark findMarkById(int id);

    Body findBodyById(int id);

    Model findModelById(int id);

    Car findCarById(int id);

    Car findCarByMarkAndModel(int idMark, int idModel);

    void save(Car car);

    void save(Mark mark);

    void save(Body body);

    void save(Model model);
}
