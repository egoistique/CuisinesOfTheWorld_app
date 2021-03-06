package com.samsung.libraryandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.domain.Meal;
import com.samsung.libraryandroid.fragment.ChangeMealFragment;
import com.samsung.libraryandroid.nodb.NoDb;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Meal> mealList;
    private Context context;

    public MealAdapter(Context context, List<Meal> mealList) {

        this.inflater = LayoutInflater.from(context);
        this.mealList = mealList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName,
                tvCountry,
                tvType,
                tvTime;
        private final LinearLayout ll_item;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            ll_item = itemView.findViewById(R.id.ll_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvType = itemView.findViewById(R.id.tv_type);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_meal_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            @SuppressLint("RecyclerView") int position
    ) {

        Meal meal = NoDb.MEAL_LIST.get(position);

        ((MyViewHolder) holder).tvName.setText(meal.getName());
        ((MyViewHolder) holder).tvCountry.setText(meal.getCountry().getName());
        ((MyViewHolder) holder).tvType.setText(meal.getType().getName());
        ((MyViewHolder) holder).tvTime.setText(meal.getTime().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeMealFragment changeClientFragment = new ChangeMealFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.MSG_NAME, mealList.get(position));
                changeClientFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, changeClientFragment)
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {

        return mealList.size();
    }
}