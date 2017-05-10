package com.yust.ariel.rvpagingtest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yust.ariel.rvpagingtest.R;
import com.yust.ariel.rvpagingtest.adapters.MoviesAdapter;
import com.yust.ariel.rvpagingtest.model.MoviesData;
import com.yust.ariel.rvpagingtest.webapi.WebApiHub;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ariel Yust on 10-May-17.
 */

public class MoviesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MoviesAdapter mRvAdapter;

    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int mPage, mTotalPages;


    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTotalPages = mPage = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRvAdapter = new MoviesAdapter(getContext());
        mRecyclerView.setAdapter(mRvAdapter);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = manager.getChildCount();
                    totalItemCount = manager.getItemCount();
                    pastVisiblesItems = manager.findFirstVisibleItemPosition();

                    if (loading) {
                        /* get the next page only after we scrolled half the page */
                        if (pastVisiblesItems >= totalItemCount / 2) {
//                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = (mPage >= mTotalPages);
                            loadPage(getContext(), mPage++);
                        }
                    }
                }
            }
        });

        loadPage(getContext(), mPage++);

        return v;
    }

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private void loadPage(Context context, int page) {
        WebApiHub.getDefault().getPopularMovies(context, page).enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, final Response<MoviesData> response) {
                if (mRvAdapter != null) {
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRvAdapter.addMovies(response.body());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {

            }
        });
    }

}
