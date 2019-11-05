package com.grace.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.grace.recycler.adapter.PokemonAdapter;
import com.grace.recycler.adapter.PokemonItemListener;
import com.grace.recycler.data.ListDataHelper;

public class MainActivity extends AppCompatActivity implements PokemonItemListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new PokemonAdapter(ListDataHelper.provideElements(), this));
    }
    @Override
    public void onPokemonClicked(int position) {
        Toast.makeText(this, "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}