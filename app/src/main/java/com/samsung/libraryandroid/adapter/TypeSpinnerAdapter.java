package com.samsung.libraryandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.domain.Type;
import com.samsung.libraryandroid.nodb.NoDb;

import java.util.List;

public class TypeSpinnerAdapter extends ArrayAdapter<Type> {

    public TypeSpinnerAdapter(@NonNull Context context,
                              @NonNull List<Type> objects) {
        super(context, R.layout.spinner_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView) convertView.findViewById(R.id.tv_spinner_item))
                .setText(NoDb.TYPE_LIST.get(position).getName());

        return convertView;
    }
}
