package com.example.esamudaay.models;

public class VendersModel {

    private String id;
    private String name;
    private int image;
    private String category1;
    private String category2;

    public VendersModel() {
    }

    public VendersModel(String id, String name, int image, String category1, String category2) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category1 = category1;
        this.category2 = category2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }
}
