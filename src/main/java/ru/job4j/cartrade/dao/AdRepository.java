package ru.job4j.cartrade.dao;

import ru.job4j.cartrade.model.Advertisement;
import ru.job4j.cartrade.model.Mark;

import java.util.List;

public interface AdRepository {

    List<Advertisement> findAdsForLastDay();

    List<Advertisement> findAdsWithPhotos();

    List<Advertisement> findAdsByMark(Mark mark);
}
