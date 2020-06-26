package com.example.popularmovies.model;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("id")
    private String trailerId;

    @SerializedName("key")
    private String trailerKey;

    @SerializedName("name")
    private String trailerName;

    @SerializedName("site")
    private String trailerSite;

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public void setTrailerName(String trailerName) {
        this.trailerName = trailerName;
    }

    public String getTrailerSite() {
        return trailerSite;
    }

    public void setTrailerSite(String trailerSite) {
        this.trailerSite = trailerSite;
    }
}
