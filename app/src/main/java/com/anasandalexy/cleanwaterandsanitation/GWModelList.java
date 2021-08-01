package com.anasandalexy.cleanwaterandsanitation;

public class GWModelList {

    public String state, level;

    public GWModelList(String state, String level) {
        this.state = state;
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
