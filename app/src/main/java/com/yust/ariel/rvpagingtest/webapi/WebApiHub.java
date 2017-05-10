package com.yust.ariel.rvpagingtest.webapi;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yust.ariel.rvpagingtest.R;
import com.yust.ariel.rvpagingtest.model.MoviesData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ariel Yust on 07-Mar-17.
 */

public class WebApiHub {
    private Retrofit mRetrofit;
    private IGitHubService mService;

    /**
     * For the sake of the exam a very simple factory style request
     * @param context
     * @param page
     * @return
     */
    public Call<MoviesData> getPopularMovies(@NonNull Context context, int page) {
        init(context);
        Resources resources = context.getResources();

        String language = "en-US";
        String sortBy = "popularity.desc";
        boolean includeAdult = false;
        boolean includeVideo = false;

        return mService.getPopularMovies(
                resources.getString(R.string.web_api_key),
                language,
                sortBy,
                includeAdult,
                includeVideo,
                page
        );
    }

    /**
     * Lazy multi-threaded safe initialization
     * @param context
     */
    private void init(Context context) {
        if (mRetrofit != null) return;
        synchronized (WebApiHub.class) {
            if (mRetrofit != null) return;

            Gson gson = new GsonBuilder().create();

            Resources resources = context.getResources();
            String baseUrl = resources.getString(R.string.web_api_base_url);

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            mService = mRetrofit.create(IGitHubService.class);
        }
    }

    /*..........................................Singleton.........................................*/
    /**
     * Singleton Implementation
     * @return the single instance of WebApiHub
     */
    public static WebApiHub getDefault() {
        return Holder.INSTANCE;
    }

    /**
     * Initialization-on-demand holder idiom,
     */
    private static class Holder {
        static final WebApiHub INSTANCE = new WebApiHub();
    }
}
