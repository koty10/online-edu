package cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar;

import org.primefaces.model.StreamedContent;

public class StudentAvatarModel {

    private Integer id;
    private String name;
    private StreamedContent image;
    private Integer price;


    // Constructor

    public StudentAvatarModel(Integer id, String name, StreamedContent image, Integer price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
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

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
