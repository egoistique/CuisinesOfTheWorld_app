package com.samsung.libraryandroid.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.domain.Country;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.domain.Time;
import com.samsung.libraryandroid.domain.Type;
import com.samsung.libraryandroid.domain.mapper.CountryMapper;
import com.samsung.libraryandroid.domain.mapper.MealMapper;
import com.samsung.libraryandroid.domain.mapper.TimeMapper;
import com.samsung.libraryandroid.domain.mapper.TypeMapper;
import com.samsung.libraryandroid.nodb.NoDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CuisinesApiImpl implements CuisinesApi{

    private final Context context;
    public static final String BASE_URL = "http://192.168.0.105:8081";

    public CuisinesApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public void fillMeal() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/meal";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            NoDb.MEAL_LIST.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Meal meal = MealMapper.mealFromJsonArray(jsonObject);
                                NoDb.MEAL_LIST.add(meal);
                            }
                            Log.d("MEAL_LIST", NoDb.MEAL_LIST.toString());
//                            ((MainActivity) context).update();
                        } catch (JSONException e) {

                            Log.d("MEAL_LIST", e.getMessage());
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }


    @Override
    public void fillCountry() {

    }

    @Override
    public void fillType() {

    }

    @Override
    public void fillTime() {

    }

    @Override
    public void addMeal(Meal meal) {

    }

    @Override
    public void updateMeal(int id, String newMealName, String newCountrylName, String newTypeName, String newTimeName) {

    }
}
