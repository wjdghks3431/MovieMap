package com.example.moviemap;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    SearchFragment searchFragment;
    BookmarkFragment bookmarkFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        bookmarkFragment = new BookmarkFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contaniners, homeFragment).commit();


        NavigationBarView navigationBarView = findViewById(R.id.btnbottom);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contaniners, homeFragment).commit();
                        return true;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contaniners, searchFragment).commit();
                        return true;
                    case R.id.bookmark:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contaniners, bookmarkFragment).commit();
                        return true;
                }
                return false;
            }
        });




    }
}
