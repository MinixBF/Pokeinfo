package com.exemple.td3_recyclerview.presentation.model;

public class RestIdResponse {

    private int id;
    private String name;
    private int height;
    private int weight;
    private int base_experience;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height){this.height = height; }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight){this.weight = weight; }

    public int getBase_experience() {
        return base_experience;
    }

}
