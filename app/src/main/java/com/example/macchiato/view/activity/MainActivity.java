package com.example.macchiato.view.activity;

import android.os.Bundle;

import com.example.macchiato.R;
import com.example.macchiato.view.fragment.recyclerviews.FavoritosFragment;
import com.example.macchiato.view.fragment.recyclerviews.FilmesFragment;
import com.example.macchiato.view.fragment.recyclerviews.LivrosFragment;
import com.example.macchiato.view.fragment.recyclerviews.NoticiasFragment;
import com.example.macchiato.view.fragment.recyclerviews.SeriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new FilmesFragment());
        getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Livros, R.id.navigation_Serie, R.id.navigation_Livros,
                R.id.navigation_Noticias, R.id.navigation_Favoritos)
                .build();


        navView.setOnNavigationItemSelectedListener(menuItem -> {

        int id = menuItem.getItemId();

        if (id == R.id.navigation_Filmes) {

            replaceFragment(new FilmesFragment());

        } else if (id == R.id.navigation_Serie) {

            replaceFragment(new SeriesFragment());

        } else if (id == R.id.navigation_Livros) {

            replaceFragment(new LivrosFragment());

        } else if (id == R.id.navigation_Noticias) {

            replaceFragment(new NoticiasFragment());

        }  else if (id == R.id.navigation_Favoritos) {

            replaceFragment(new FavoritosFragment());
        }

        return true;
    });

}

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

}
