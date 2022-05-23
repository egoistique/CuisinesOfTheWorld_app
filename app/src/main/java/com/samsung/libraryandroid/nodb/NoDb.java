package com.samsung.libraryandroid.nodb;

import com.samsung.libraryandroid.domain.Country;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.domain.Time;
import com.samsung.libraryandroid.domain.Type;

import java.util.ArrayList;
import java.util.List;

public class NoDb {
    private NoDb() {
    }

    public static final List<Meal> MEAL_LIST = new ArrayList<>();

    public static final List<Country> COUNTRY_LIST = new ArrayList<>();

    public static final List<Type> TYPE_LIST = new ArrayList<>();

    public static final List<Time> TIME_LIST = new ArrayList<>();

}
