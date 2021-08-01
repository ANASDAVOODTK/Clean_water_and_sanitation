package com.anasandalexy.cleanwaterandsanitation;

public class LABModelList {

    public String state, level, lname;

    public LABModelList(String state, String level, String lname) {
        this.state = state;
        this.level = level;
        this.lname = lname;
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

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
