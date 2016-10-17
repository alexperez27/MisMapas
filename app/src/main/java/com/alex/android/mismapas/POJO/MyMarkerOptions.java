package com.alex.android.mismapas.POJO;

import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alexander on 10-16-16.
 */
public class MyMarkerOptions {
    private int id;
    private MarkerOptions markerOptions;

    public MyMarkerOptions() {
        this.markerOptions= new MarkerOptions();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
