package com.samsung.libraryandroid.domain.mapper;

import com.samsung.libraryandroid.domain.Time;

import org.json.JSONException;
import org.json.JSONObject;

public class TimeMapper {
    public static Time timeFromMealJsonArray(JSONObject jsonObject) {

        Time time = null;
        try {

            time = new Time(
                    jsonObject.getJSONObject("timeDto").getInt("id"),
                    jsonObject.getJSONObject("timeDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return time;
    }
}
