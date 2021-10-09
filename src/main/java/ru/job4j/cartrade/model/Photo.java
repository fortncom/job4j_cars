package ru.job4j.cartrade.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String path;
    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    public static Photo of(String name) {
        Photo photo = new Photo();
        photo.setName(name);
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        return id == photo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Photo{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", path='" + path + '\''
                + '}';
    }
}
