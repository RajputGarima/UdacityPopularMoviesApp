package com.example.anurag.popularmovies1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import com.example.anurag.popularmovies1.Fragment.DetailFragment;

public class DetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    CardView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       // toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.layout_detail_container,new DetailFragment());


    }
}
