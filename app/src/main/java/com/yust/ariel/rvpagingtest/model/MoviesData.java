package com.yust.ariel.rvpagingtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ariel Yust on 07-Mar-17.
 */
public class MoviesData {

    @SerializedName("page")
    @Expose
    private int mPage;

    @SerializedName("results")
    @Expose
    private List<MovieInfoData> mResults;

    @SerializedName("total_results")
    @Expose
    private int mTotalResults;

    @SerializedName("total_pages")
    @Expose
    private int mTotalPages;

    public int getPage() {
        return mPage;
    }

    public List<MovieInfoData> getResults() {
        return mResults;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public MoviesData(int mPage, List<MovieInfoData> mResults, int mTotalResults, int mTotalPages) {
        this.mPage = mPage;
        this.mResults = mResults;
        this.mTotalResults = mTotalResults;
        this.mTotalPages = mTotalPages;
    }
}
