package com.example.mymoviebrowser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        DetailsFragment detailsFragment = DetailsFragment.newInstance(movie);

        fts.add(R.id.container, detailsFragment);
        fts.commit();
    }
}