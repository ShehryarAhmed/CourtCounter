package com.example.android.petapp;

/**
 * Created by android on 10/30/2016.
 */
public class Pets {
    private String Pet_name;
    private String Pet_breed;
    private int Pet_Weight;
    private int gender;

    public Pets(String pet_name, String pet_breed, int gender, int pet_Weight) {
        this.Pet_name = pet_name;
        this.Pet_breed = pet_breed;
        this.gender = gender;
        this.Pet_Weight = pet_Weight;
    }

    public Pets() {

    }

    public void setPet_name(String pet_name) {
        Pet_name = pet_name;
    }

    public void setPet_breed(String pet_breed) {
        Pet_breed = pet_breed;
    }

    public void setPet_Weight(int pet_Weight) {
        Pet_Weight = pet_Weight;
    }

    public void setPetGender(int gender) {
        this.gender = gender;
    }

    public String getPet_name() {
        return Pet_name;
    }

    public String getPet_breed() {
        return Pet_breed;
    }

    public int getPet_Weight() {
        return Pet_Weight;
    }

    public int getPetGender() {
        return gender;
    }
}
