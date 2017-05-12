package com.example.pc.ChineseChow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 3/27/2017.
 */

public class  Recipe {

    private String cookTime;
    private String prepTime;
    private String recipeName;
    private String recipe;
    private ArrayList<String> Ingredients;
    private String servingSize;
    private String imageUri;
    public Recipe(){}

    public Recipe(String cookTime, String prepTime, String recipeName, String recipe, ArrayList<String> Ingredients, String servingSize, String imageUri){

        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.recipeName = recipeName;

        this.recipe = recipe;
        this.servingSize = servingSize;
        this.Ingredients = Ingredients;

        this.imageUri = imageUri;
    }

    public String getCookTime(){
        return cookTime;
    }

    public void setCookTime(String cookTime) {this.cookTime=cookTime;}

    public String getPrepTime(){
        return prepTime;
    }

    public void setPrepTime(String prepTime) {this.prepTime=prepTime; }

    public String getRecipeName(){
        return recipeName;
    }

    public void setRecipeName(String recipeName){ this.recipeName= recipeName;}

    public String getRecipe(){
        return recipe;
    }

    public void setRecipe(String recipe){
        this.recipe = recipe;
    }

    public ArrayList<String> getIngredients() { return Ingredients; }

    public void setIngredients(ArrayList Ingredients)
    {
        this.Ingredients = Ingredients;
    }


    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
