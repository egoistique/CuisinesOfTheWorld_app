package com.samsung.libraryandroid;

import static com.samsung.libraryandroid.nodb.NoDb.MEAL_LIST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.samsung.libraryandroid.adapter.MealAdapter;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.fragment.AddMealFragment;
import com.samsung.libraryandroid.nodb.NoDb;
import com.samsung.libraryandroid.rest.CuisinesApi;
import com.samsung.libraryandroid.rest.CuisinesApiImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    public static final String MSG_NAME = "mealFromListByPos";

    private AppCompatButton fab;

    private FragmentTransaction transaction;

    private MealAdapter mealAdapter;

    private RecyclerView rvMeals;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback;

    private final CuisinesApi cuisinesApi = new CuisinesApiImpl(this);

    @Override
    protected void onResume() {
        super.onResume();

        cuisinesApi.fillMeal();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuisinesApi.fillMeal();
        cuisinesApi.fillCountry();
        cuisinesApi.fillType();
        cuisinesApi.fillTime();


        rvMeals = findViewById(R.id.rv_meals);
        mealAdapter = new MealAdapter(this, MEAL_LIST);
        rvMeals.setAdapter(mealAdapter);

        fab = findViewById(R.id.btn_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddMealFragment addMealFragment = new AddMealFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, addMealFragment)
                        .commit();
            }
        });



        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                Meal meal = NoDb.MEAL_LIST.get(viewHolder.getAdapterPosition());

                if (direction == ItemTouchHelper.LEFT) {
                    Toast.makeText(MainActivity.this, "Удалено",
                            Toast.LENGTH_SHORT).show();
                    cuisinesApi.deleteMeal(meal.getId());
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvMeals);
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        if (size > 0)
            getSupportFragmentManager().beginTransaction().remove(fragments.get(size-1)).commit();
        else
            finish();
    }


    public void update() {

        mealAdapter.notifyDataSetChanged();
   }
}