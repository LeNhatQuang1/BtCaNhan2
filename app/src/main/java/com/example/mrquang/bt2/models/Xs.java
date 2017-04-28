package com.example.mrquang.bt2.models;

import java.io.Serializable;

/**
 * Created by MrQuang on 28/04/2017.
 */

public class Xs implements Serializable {
    String date;
    Giai giai;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Giai getGiai() {
        return giai;
    }

    public void setGiai(Giai giai) {
        this.giai = giai;
    }
}
