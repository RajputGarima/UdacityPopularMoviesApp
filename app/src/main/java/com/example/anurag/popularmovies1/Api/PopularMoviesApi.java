package com.example.anurag.popularmovies1.Api;

import com.example.anurag.popularmovies1.Api.Model.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anurag on 10/9/2016.
 */

public interface PopularMoviesApi {

    @GET("3/movie/{sort}?api_key=MyApiKey")
    Call<MoviesModel> getData(@Path("sort") String sort);

}
