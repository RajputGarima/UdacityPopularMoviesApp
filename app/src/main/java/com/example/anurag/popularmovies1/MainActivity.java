package com.example.anurag.popularmovies1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.anurag.popularmovies1.Fragment.FragmentMainActivity;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FragmentMainActivity mFragmentActivity;
    Boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentActivity = new FragmentMainActivity();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.layout_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                   .replace(R.id.layout_detail_container, new FragmentMainActivity())
                   .commit();
            }
        }else {
            mTwoPane=false;
        }
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new FragmentMainActivity(), TAG)
//                    .commit();
//        }


    }

}
