package com.yust.ariel.rvpagingtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ariel Yust on 07-Mar-17.
 */
public class MovieInfoData {

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("adult")
    @Expose
    boolean isAdultFilm;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("genre_ids")
    @Expose
    private int[] genreIds;

    @SerializedName("id")
    @Expose
    private int mid;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("popularity")
    @Expose
    private float popularity;

    @SerializedName("vote_count")
    @Expose
    private int voteCount;

    @SerializedName("video")
    @Expose
    private boolean hasVideo;

    @SerializedName("vote_average")
    @Expose
    private float voteAverage;

    public int getId() {
        return mid;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public MovieInfoData(String posterPath, boolean isAdultFilm, String overview, String releaseDate, int[] genreIds, int mid, String originalTitle, String originalLanguage, String title, String backdropPath, float popularity, int voteCount, boolean hasVideo, float voteAverage) {
        this.posterPath = posterPath;
        this.isAdultFilm = isAdultFilm;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.mid = mid;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.hasVideo = hasVideo;
        this.voteAverage = voteAverage;
    }
}
