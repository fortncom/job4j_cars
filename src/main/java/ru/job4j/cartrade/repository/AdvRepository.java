package ru.job4j.cartrade.repository;

import ru.job4j.cartrade.model.*;

import java.util.List;

public interface AdvRepository {

    List<Advertisement> findAllAds();

    List<Advertisement> findAdsForLastDay();

    List<Advertisement> findAdsWithPhotos();

    List<Advertisement> findAdsByMark(Mark mark);

    List<Advertisement> findAdsByFilter(Filter filter);

    Photo findPhotoById(int id);

    Photo findPhotoByName(String name);

    Advertisement findAdvById(int id);

    void save(Advertisement adv);

    void save(Photo photo);
}
