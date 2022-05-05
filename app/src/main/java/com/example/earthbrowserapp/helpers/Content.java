package com.example.earthbrowserapp.helpers;

public class Content {

//    properties
    private int contentImage;
    private String contentTitle;
    private String contentMessage;

//    constructors

    public Content() {
    }

    public Content(int contentImage, String contentTitle, String contentMessage) {
        this.contentImage = contentImage;
        this.contentTitle = contentTitle;
        this.contentMessage = contentMessage;
    }



    //    getters and setters

    public int getContentImage() {
        return contentImage;
    }

    public void setContentImage(int contentImage) {
        this.contentImage = contentImage;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }
}
