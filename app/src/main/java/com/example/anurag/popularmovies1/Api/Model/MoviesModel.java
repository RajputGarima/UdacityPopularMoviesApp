package com.example.anurag.popularmovies1.Api.Model;

import java.util.List;

/**
 * Created by anurag on 10/9/2016.
 */

public class MoviesModel {

    List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
