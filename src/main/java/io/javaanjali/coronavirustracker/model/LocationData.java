package io.javaanjali.coronavirustracker.model;

public class LocationData {
    private String state,country;
    private int today_total,prev_total,dif_fromprevday;

    public LocationData() {
    }

    public int getDif_fromprevday() {
        return dif_fromprevday;
    }

    public void setDif_fromprevday(int dif_fromprevday) {
        this.dif_fromprevday = dif_fromprevday;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", today_total=" + today_total +
                ", prev_total=" + prev_total +
                '}';
    }

    public int getToday_total() {
        return today_total;
    }

    public void setToday_total(int today_total) {
        this.today_total = today_total;
    }

    public int getPrev_total() {
        return prev_total;
    }

    public void setPrev_total(int prev_total) {
        this.prev_total = prev_total;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }






}
