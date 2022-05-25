package com.samsung.libraryandroid.domain.mapper;

import com.samsung.libraryandroid.domain.Country;

import org.json.JSONException;
import org.json.JSONObject;

public class CountryMapper {
    public static Country countryFromMealJsonArray(JSONObject jsonObject) {

        Country country = null;
        try {

            country = new Country(
                    jsonObject.getJSONObject("countryDto").getInt("id"),
                    jsonObject.getJSONObject("countryDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return country;
    }

    public Country countryFromJsonArray(JSONObject jsonObject) {

        Country country = null;
        try {

            country = new Country(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return country;
    }
}
