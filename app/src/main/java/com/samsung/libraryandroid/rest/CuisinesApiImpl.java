package com.samsung.libraryandroid.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
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

import java.util.HashMap;
import java.util.Map;

public class CuisinesApiImpl implements CuisinesApi{

    public static final String API_TEST = "API_TEST";
    private final Context context;
    public static final String BASE_URL = "http://192.168.0.105:8081";

    private Response.ErrorListener errorListener;

    public CuisinesApiImpl(Context context) {
        this.context = context;
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(API_TEST, error.toString());
            }
        };
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

                                Time time = new TimeMapper().timeFromMealJsonArray(jsonObject);

                                Type type = new TypeMapper().typeFromMealJsonArray(jsonObject);

                                Country country = new CountryMapper().countryFromMealJsonArray(jsonObject);

                                Meal meal = new MealMapper().mealFromJsonArray(jsonObject);
                                NoDb.MEAL_LIST.add(meal);
                            }
                            Log.d(API_TEST, NoDb.MEAL_LIST.toString());
                            ((MainActivity) context).update();
                        } catch (JSONException e) {
                            Log.d("MEAl_LIST", e.getMessage());
                        }

                    }
                }, errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillCountry() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/country";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Country country = new CountryMapper().countryFromJsonArray(jsonObject);

                                NoDb.COUNTRY_LIST.add(country);
                            }
                            Log.d("COUNTRY_LIST", NoDb.COUNTRY_LIST.toString());
                        } catch (JSONException e) {
                            Log.d("COUNTRY_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillType() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/type";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Type type = new TypeMapper().typeFromJsonArray(jsonObject);

                                NoDb.TYPE_LIST.add(type);
                            }
                            Log.d("TYPE_LIST", NoDb.TYPE_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("TYPE_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillTime() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/time";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Time time = new TimeMapper().timeFromJsonArray(jsonObject);

                                NoDb.TIME_LIST.add(time);
                            }
                            Log.d("TIME_LIST", NoDb.TIME_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("TIME_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void addMeal(Meal meal) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/meal";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillMeal();
                        Log.d(API_TEST, response);
                    }
                },
                errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("nameMeal", meal.getName());
                params.put("nameCountry", meal.getCountry().getName());
                params.put("nameType", meal.getType().getName());
                params.put("nameTime", meal.getTime().getName());
                return params;
            }
        };
        queue.add(postRequest);
    }

    @Override
    public void updateMeal(int id, String newMealName, String newCountryName, String newTypeName, String newTimeName) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/meal/" + id + "/";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        fillMeal();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("newMealName", newMealName);
                params.put("newCountryName", newCountryName);
                params.put("newTypeName", newTypeName);
                params.put("newTimeName", newTimeName);
                params.put("id", String.valueOf(id));
                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void deleteMeal(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/meal/" + id;

        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fillMeal();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(dr);
    }
}