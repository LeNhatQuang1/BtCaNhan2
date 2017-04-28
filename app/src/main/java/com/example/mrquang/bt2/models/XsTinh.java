package com.example.mrquang.bt2.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MrQuang on 28/04/2017.
 */

public class XsTinh implements Serializable{
    String province;
    List<Xs> xs;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<Xs> getXs() {
        return xs;
    }

    public void setXs(List<Xs> xs) {
        this.xs = xs;
    }
}
