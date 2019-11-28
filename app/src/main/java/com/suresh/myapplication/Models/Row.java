package com.suresh.myapplication.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    // Model for data fetched from server, Retrofit - Gson converter converts json to java object
    // Gson uses to convert json to java objects and vice versa

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

}
