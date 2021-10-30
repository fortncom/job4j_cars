package ru.job4j.cartrade.service;

import ru.job4j.cartrade.model.*;
import ru.job4j.cartrade.repository.AdvRepository;
import ru.job4j.cartrade.repository.AdvRepositoryImpl;

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
        AdvRepository repository = AdvRepositoryImpl.instOf();
        if (adv.getId() == 0) {
            adv.setCreated(new Date());
        } else {
            adv.setCreated(repository.findAdvById(adv.getId()).getCreated());
        }
        List<String> photo = Arrays.asList(advPhoto.split("\\|"));
        List<Photo> photos = new ArrayList<>();
        for (String name : photo) {
            Photo p = repository.findPhotoByName(name);
            if (p == null) {
                p = Photo.of(name);
                repository.save(p);
            }
            photos.add(p);
        }
        adv.setPhotos(photos);
        Car car = repository.findCarByMarkAndModel(markId, modelId);
        if (car == null) {
            Mark mark = repository.findMarkById(markId);
            Model model = repository.findModelById(modelId);
            car = Car.of(mark, model);
            repository.save(car);
        }
        adv.setCar(car);
        repository.save(adv);
    }
}
