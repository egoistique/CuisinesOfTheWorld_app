package com.samsung.libraryandroid.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.domain.Country;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.domain.Time;
import com.samsung.libraryandroid.domain.Type;
import com.samsung.libraryandroid.nodb.NoDb;
import com.samsung.libraryandroid.rest.CuisinesApiImpl;

import java.util.List;

public class TimeSpinnerAdapter extends ArrayAdapter<Time>{
    public TimeSpinnerAdapter(@NonNull Context context,
                              @NonNull List<Time> objects) {
        super(context, R.layout.spinner_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView) convertView.findViewById(R.id.tv_spinner_item))
                .setText(NoDb.TIME_LIST.get(position).getName());

        return convertView;
    }
}
