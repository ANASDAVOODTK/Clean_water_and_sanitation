package com.anasandalexy.cleanwaterandsanitation;

public class RainfallModelList {

    public String subdivision,_year,annual;

    public RainfallModelList(String subdivision, String _year, String annual) {
        this.subdivision = subdivision;
        this._year = _year;
        this.annual = annual;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String get_year() {
        return _year;
    }

    public void set_year(String _year) {
        this._year = _year;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }
}
