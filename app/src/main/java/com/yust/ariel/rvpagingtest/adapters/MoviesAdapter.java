package com.yust.ariel.rvpagingtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yust.ariel.rvpagingtest.R;
import com.yust.ariel.rvpagingtest.model.MovieInfoData;
import com.yust.ariel.rvpagingtest.model.MoviesData;

import timber.log.Timber;


/**
 * Created by Ariel Yust on 10-May-17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private MoviesData mMoviesData;
    private Context mContext;
    private String mStrImgPath;
    private int mMaxPage;

    public MoviesAdapter(@NonNull Context context) {
        mContext = context;
        mStrImgPath = context.getResources().getString(R.string.web_api_image_base_url);
    }

    public void addMovies(MoviesData newMoviesData) {
        int newMoviesSize, oldMoviesSize;
        if (mMoviesData == null) {
            mMoviesData = newMoviesData;
            oldMoviesSize = 0;
            newMoviesSize = mMoviesData.getResults().size();
            notifyDataSetChanged();
        } else {
            newMoviesSize = newMoviesData.getResults().size();
            oldMoviesSize = mMoviesData.getResults().size();
            mMoviesData.getResults().addAll(newMoviesData.getResults());
            notifyItemRangeInserted(oldMoviesSize, newMoviesSize);
        }
        mMaxPage = mMoviesData.getPage();
        Timber.d("MoviesAdapter", "Added " + newMoviesSize + " new movies");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_movie, parent, false);

        /* Return a new holder instance */
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /* Exit condition */
        if (mMoviesData == null) return;

        /* Get the data model based on position */
        final MovieInfoData info = mMoviesData.getResults().get(position);

        /* Set poster based on data model */
        final ImageView poster = holder.getPoster();

        Glide.with(mContext)
                .load(mStrImgPath + info.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(poster);
    }

    @Override
    public int getItemCount() {
        if (mMoviesData == null) return 0;
        return mMoviesData.getResults().size();
    }

    /**
     * Provide a direct reference to each of the views within a data item
     * Used to cache the views within the item layout for fast access
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPoster;

        public ImageView getPoster() {
            return mPoster;
        }

        /**
         * We also create a constructor that accepts the entire item row
         * and does the view lookups to find each subview
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            mPoster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
