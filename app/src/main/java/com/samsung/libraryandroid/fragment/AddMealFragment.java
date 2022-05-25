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

public class AddMealFragment extends Fragment {

    private EditText etMealName;
    private AppCompatSpinner sp_country;
    private AppCompatSpinner sp_type;
    private AppCompatSpinner sp_time;
    private AppCompatButton btnAdd;

    private Meal meal;

    @Override
    public void onResume() {
        super.onResume();

        if (etMealName != null) {
            etMealName.setText("");
        }
    }

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

        View view = inflater.inflate(R.layout.fragment_add_meal, container, false);

        etMealName = view.findViewById(R.id.et_mealName);

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

        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!checkEmpty()) {
                    new CuisinesApiImpl(getContext()).addMeal(
                            new Meal(etMealName.getText().toString(),
                            (Country) sp_country.getSelectedItem(),
                            (Type) sp_type.getSelectedItem(),
                            (Time) sp_time.getSelectedItem())
                    );
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