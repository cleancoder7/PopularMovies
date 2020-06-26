package com.example.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditApiResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("cast")
    private List<Cast> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
