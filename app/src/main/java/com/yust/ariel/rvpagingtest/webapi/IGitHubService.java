package com.yust.ariel.rvpagingtest.webapi;

import com.yust.ariel.rvpagingtest.model.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ariel Yust on 07-Mar-17.
 */

public interface IGitHubService {

    @GET("discover/movie")
    Call<MoviesData> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("include_adult") boolean includeAdult,
            @Query("include_video") boolean includeVideo,
            @Query("page") int page
    );
}
