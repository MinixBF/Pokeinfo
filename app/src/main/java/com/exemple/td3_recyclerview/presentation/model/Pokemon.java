package com.exemple.td3_recyclerview.presentation.model;

public class Pokemon {
    private int number;
    private String url;
    private String name;
    private int height;
    private int weight;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public int getNumber(){
        String[] urlPart = url.split("/");
        return Integer.parseInt(urlPart[urlPart.length - 1]);
    }
    public void setNumber(int number){
        this.number = number;
    }

    public int getHeight()
    {
        return height;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWeight()
    {
        return weight;
    }
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

}
