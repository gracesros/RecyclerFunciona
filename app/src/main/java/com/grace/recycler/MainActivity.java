package com.grace.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.grace.recycler.adapter.PokemonAdapter;
import com.grace.recycler.adapter.PokemonItemListener;
import com.grace.recycler.data.AppDataBaseSingleton;
import com.grace.recycler.data.AppDatabase;
import com.grace.recycler.data.PokemonShort;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PokemonItemListener {
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 3);
        recyclerView.setLayoutManager(manager);
        adapter = new PokemonAdapter(new ArrayList<PokemonShort>(), this);
        recyclerView.setAdapter(adapter);
    }

    private void updatePokemonList(List<PokemonShort> pokemonShortList) {
        adapter.setItems(pokemonShortList);
    }

    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    public void onPokemonClicked(int position) {
        PokemonShort pokemonShort = adapter.getItem(position);
        Intent intent = new Intent(this, PokemonDetailActivity.class);
        intent.putExtra(EXTRA_DATA, pokemonShort);
        startActivity(intent);
    }

    public void addPokemon(View view) {
        Intent intent = new Intent(this, AddPokemonActivity.class);
        startActivity(intent);
    }

    private class GetPokemonsAsyncTask extends AsyncTask<Void, Void, List<PokemonShort>> {
        @Override
        protected List<PokemonShort> doInBackground(Void... voids) {
            AppDatabase db = AppDataBaseSingleton.getInstance(getApplicationContext()).appDatabase;
            return db.pokemonDao().getAll();
        }

        @Override
        protected void onPostExecute(List<PokemonShort> pokemonShortList) {
            super.onPostExecute(pokemonShortList);
            if (pokemonShortList != null) {
                updatePokemonList(pokemonShortList);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetPokemonsAsyncTask task = new GetPokemonsAsyncTask();
        task.execute();
    }
}
