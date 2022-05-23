package com.samsung.libraryandroid.domain.mapper;

import com.samsung.libraryandroid.domain.Type;

import org.json.JSONException;
import org.json.JSONObject;

public class TypeMapper {
    public static Type typeFromMealJsonArray(JSONObject jsonObject) {

        Type type = null;
        try {

            type = new Type(
                    jsonObject.getJSONObject("typeDto").getInt("id"),
                    jsonObject.getJSONObject("typeDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return type;
    }
}
