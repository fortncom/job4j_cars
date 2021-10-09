package ru.job4j.cartrade.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean sold;

    @OneToMany(mappedBy = "advertisements")
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Advertisement of(
            String description, Boolean sold, List<Photo> photos, Car car, User user) {
        Advertisement adv = new Advertisement();
        adv.description = description;
        adv.sold = sold;
        adv.photos = photos;
        adv.car = car;
        adv.user = user;
        return adv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advertisement{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", sold=" + sold
                + ", photo=" + photos
                + ", car=" + car
                + ", user=" + user
                + '}';
    }
}
