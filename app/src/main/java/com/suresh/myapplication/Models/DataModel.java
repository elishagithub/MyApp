package com.suresh.myapplication.Models;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    // Model for data fetched from server, Retrofit - Gson converter converts json to java object
    // Gson uses to convert json to java objects and vice versa

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

    public String getTitle() {
        return title;
    }

    public List<Row> getRows() {
        return rows;
    }

}
