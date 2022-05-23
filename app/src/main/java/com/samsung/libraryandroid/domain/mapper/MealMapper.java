package com.samsung.libraryandroid.domain.mapper;

import com.samsung.libraryandroid.domain.Country;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.domain.Time;
import com.samsung.libraryandroid.domain.Type;

import org.json.JSONException;
import org.json.JSONObject;

public class MealMapper {
    public static Meal mealFromJsonArray(JSONObject jsonObject) {

        Meal meal = null;

        try {
            meal = new Meal(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    CountryMapper.countryFromMealJsonArray(jsonObject),
                    TypeMapper.typeFromMealJsonArray(jsonObject),
                    TimeMapper.timeFromMealJsonArray(jsonObject)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return meal;
    }
}
