package ru.job4j.cartrade.repository;

import ru.job4j.cartrade.model.*;

import java.util.List;

public interface AdvRepository {

    List<Advertisement> findAllAds();

    List<Advertisement> findAdsForLastDay();

    List<Advertisement> findAdsWithPhotos();

    List<Advertisement> findAdsByMark(Mark mark);

    List<Advertisement> findAdsByFilter(Filter filter);

    List<Car> findAllCars();

    User findUserByEmail(String email);

    Role findRoleById(int id);

    Mark findMarkById(int id);

    Body findBodyById(int id);

    Model findModelById(int id);

    Car findCarById(int id);

    Car findCarByMarkAndModel(int idMark, int idModel);

    Photo findPhotoById(int id);

    Photo findPhotoByName(String name);

    Advertisement findAdvById(int id);

    void save(Advertisement adv);

    void save(User user);

    void save(Role role);

    void save(Photo photo);

    void save(Car car);

    void save(Mark mark);

    void save(Body body);

    void save(Model model);
}
