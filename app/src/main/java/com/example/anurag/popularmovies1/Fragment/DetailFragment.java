package com.example.anurag.popularmovies1.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anurag.popularmovies1.R;

/**
 * Created by anurag on 10/12/2016.
 */

public class DetailFragment extends Fragment {
    public static String FRAG_TAG = "fragdet";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
}
