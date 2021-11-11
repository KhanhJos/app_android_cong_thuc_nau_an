package com.example.appnauan.Model;

public class MonAn {
    int id;
    String name;
    String cookingRecipe;
    String image;

    public MonAn(int id, String name, String cookingRecipe, String image) {
        this.id = id;
        this.name = name;
        this.cookingRecipe = cookingRecipe;
        this.image = image;
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

    public String getCookingRecipe() {
        return cookingRecipe;
    }

    public void setCookingRecipe(String cookingRecipe) {
        this.cookingRecipe = cookingRecipe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
