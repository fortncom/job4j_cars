package ru.job4j.cartrade.model;

import java.util.Objects;

public class Filter {

    private int idMark;
    private int idModel;
    private int priceMin;
    private int priceMax;

    public Filter() {
    }

    public static Filter of(int idMark, int idModel, int priceMin, int priceMax) {
        Filter filter = new Filter();
        filter.idMark = idMark;
        filter.idModel = idModel;
        filter.priceMin = priceMin;
        filter.priceMax = priceMax;
        return filter;
    }

    public int getIdMark() {
        return idMark;
    }

    public void setIdMark(int idMark) {
        this.idMark = idMark;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Filter filter = (Filter) o;
        return idMark == filter.idMark
                && idModel == filter.idModel
                && priceMin == filter.priceMin
                && priceMax == filter.priceMax;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMark, idModel, priceMin, priceMax);
    }

    @Override
    public String toString() {
        return "Filter{"
                + "idMark=" + idMark
                + ", idModel=" + idModel
                + ", priceMin=" + priceMin
                + ", priceMax=" + priceMax
                + '}';
    }
}
