package com.grace.recycler.data;

import java.util.List;

public class Pokemon {
    int id;
    int height;
    int baseExperience;
    int weight;
    String url;
    String name;

    List<String> types;

    public Pokemon(String name, String id) {
        this.name = name;
        this.url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + id + ".png";
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
