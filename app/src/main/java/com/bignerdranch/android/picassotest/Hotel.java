package com.bignerdranch.android.picassotest;

public class Hotel {

    private String imageURL;
    private String hotelDesc;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getHotelDesc() {
        return hotelDesc;
    }

    public void setHotelDesc(String hotelDesc) {
        this.hotelDesc = hotelDesc;
    }

    public Hotel(String imageURL, String hotelDesc) {

        this.imageURL = imageURL;
        this.hotelDesc = hotelDesc;
    }
}
