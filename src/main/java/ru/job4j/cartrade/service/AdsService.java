package ru.job4j.cartrade.service;

import ru.job4j.cartrade.model.*;
import ru.job4j.cartrade.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdsService {

    public List<Advertisement> findByFilter(
            int mark, int model, int minPrice, int maxPrice) {
        Filter filter = Filter.of(mark, model, minPrice, maxPrice);
        return AdvRepositoryImpl.instOf().findAdsByFilter(filter);
    }

    public void saveAdv(Advertisement adv, int markId, int  modelId, String advPhoto) {
        AdvRepository advRepository = AdvRepositoryImpl.instOf();
        if (adv.getId() == 0) {
            adv.setCreated(new Date());
        } else {
            adv.setCreated(advRepository.findAdvById(adv.getId()).getCreated());
        }
        List<String> photo = Arrays.asList(advPhoto.split("\\|"));
        List<Photo> photos = new ArrayList<>();
        for (String name : photo) {
            Photo p = advRepository.findPhotoByName(name);
            if (p == null) {
                p = Photo.of(name);
                advRepository.save(p);
            }
            photos.add(p);
        }
        adv.setPhotos(photos);
        CarRepository carRepository = CarRepositoryImpl.instOf();
        Car car = carRepository.findCarByMarkAndModel(markId, modelId);
        if (car == null) {
            Mark mark = carRepository.findMarkById(markId);
            Model model = carRepository.findModelById(modelId);
            car = Car.of(mark, model);
            carRepository.save(car);
        }
        adv.setCar(car);
        advRepository.save(adv);
    }

    public User findUserByEmail(String email) {
        return UserRepositoryImpl.instOf().findUserByEmail(email);
    }

    public void saveUser(String name, String email, String password, int roleId) {
        UserRepositoryImpl userRepository = UserRepositoryImpl.instOf();
        Role role = userRepository.findRoleById(roleId);
        userRepository.save(User.of(name, email, password, role));
    }

    public List<Advertisement> findAllAds() {
        return AdvRepositoryImpl.instOf().findAllAds();
    }

    public List<Car> findAllCars() {
       return CarRepositoryImpl.instOf().findAllCars();
    }

    public Advertisement findAdvById(int id) {
        AdvRepository advRepository = AdvRepositoryImpl.instOf();
        return advRepository.findAdvById(id);
    }
}
