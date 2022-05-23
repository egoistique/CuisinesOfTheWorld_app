package com.samsung.libraryandroid;

import static com.samsung.libraryandroid.nodb.NoDb.MEAL_LIST;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.samsung.libraryandroid.adapter.MealAdapter;
import com.samsung.libraryandroid.rest.CuisinesApi;
import com.samsung.libraryandroid.rest.CuisinesApiImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CuisinesApiImpl(this).fillMeal();
    }

//    public static final String MSG_NAME = "mealFromListByPos";
//
//    private AppCompatButton fab;
//
//    private FragmentTransaction transaction;
//
//    private MealAdapter bookAdapter;
//
//    private RecyclerView rvMeels;
//
//    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback;
//
//    private final CuisinesApi libraryApi = new CuisinesApiImpl(this);
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        libraryApi.fillMeal();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        rvMeels = findViewById(R.id.rv_meals);
//        bookAdapter = new MealAdapter(this, MEAL_LIST);
//        rvMeels.setAdapter(bookAdapter);
//    }
//
    public void update() {
//
//        bookAdapter.notifyDataSetChanged();
    }
}