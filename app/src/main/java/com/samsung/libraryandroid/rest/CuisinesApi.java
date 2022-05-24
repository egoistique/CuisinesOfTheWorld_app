package com.samsung.libraryandroid.rest;

import com.samsung.libraryandroid.domain.Meal;

public interface CuisinesApi {
    void fillMeal();
    void  fillCountry();
    void  fillType();
    void  fillTime();
    void addMeal(Meal meal);
    void updateMeal(
            int id,
            String newMealName,
            String newCountrylName,
            String newTypeName,
            String newTimeName
    );
    void deleteMeal(int id);
}
