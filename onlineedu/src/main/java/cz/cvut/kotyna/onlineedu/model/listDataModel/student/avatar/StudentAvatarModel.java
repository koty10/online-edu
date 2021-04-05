package cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar;

import org.primefaces.model.StreamedContent;

public class StudentAvatarModel {

    private Integer id;
    private String name;
    private String image;
    private Integer price;
    private String expiration;
    private boolean active;


    // Constructor


    public StudentAvatarModel(Integer id, String name, String image, Integer price, String expiration, boolean active) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.expiration = expiration;
        this.active = active;
    }

    public StudentAvatarModel() {

    }

    // Getters & Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
