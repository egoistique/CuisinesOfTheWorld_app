package com.samsung.libraryandroid.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.adapter.CountrySpinnerAdapter;
import com.samsung.libraryandroid.adapter.TimeSpinnerAdapter;
import com.samsung.libraryandroid.adapter.TypeSpinnerAdapter;
import com.samsung.libraryandroid.domain.Country;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.domain.Time;
import com.samsung.libraryandroid.domain.Type;
import com.samsung.libraryandroid.nodb.NoDb;
import com.samsung.libraryandroid.rest.CuisinesApiImpl;

public class ChangeMealFragment extends Fragment {

    private EditText etMealName;
    private AppCompatSpinner sp_country;
    private AppCompatSpinner sp_type;
    private AppCompatSpinner sp_time;

    private boolean checkEmpty() {
        boolean problem = false;

        if (TextUtils.isEmpty(etMealName.getText().toString())) {
            etMealName.setError("Обязательное поле");
            problem = true;
        }

        return problem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_meal, container, false);

        etMealName = view.findViewById(R.id.et_mealName);

        Meal meal = (Meal) (getArguments().getSerializable(MainActivity.MSG_NAME));

        etMealName.setText(meal.getName());

        sp_country = view.findViewById(R.id.sp_country);
        CountrySpinnerAdapter countrySpinnerAdapter =
                new CountrySpinnerAdapter(
                        getActivity(),
                        NoDb.COUNTRY_LIST
                );
        sp_country.setAdapter(countrySpinnerAdapter);

        sp_type = view.findViewById(R.id.sp_type);
        TypeSpinnerAdapter typeSpinnerAdapter =
                new TypeSpinnerAdapter(
                        getActivity(),
                        NoDb.TYPE_LIST
                );
        sp_type.setAdapter(typeSpinnerAdapter);

        sp_time = view.findViewById(R.id.sp_time);
        TimeSpinnerAdapter timeSpinnerAdapter =
                new TimeSpinnerAdapter(
                        getActivity(),
                        NoDb.TIME_LIST
                );
        sp_time.setAdapter(timeSpinnerAdapter);

        AppCompatButton btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!checkEmpty()) {
                    new CuisinesApiImpl(getActivity())
                            .updateMeal(
                                    meal.getId(),
                                    etMealName.getText().toString(),
                                    ((Type) sp_type.getSelectedItem()).getName(),
                                    ((Country) sp_country.getSelectedItem()).getName(),
                                    ((Time) sp_time.getSelectedItem()).getName()
                            );


                    getActivity().getSupportFragmentManager().beginTransaction().remove(ChangeMealFragment.this).commit();

                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        ((MainActivity) getActivity()).update();
    }
}