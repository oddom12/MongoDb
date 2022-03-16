package com.example.MongoDb.model;

public class PostNotificationModel {


    private String ip_adress ;
    private String message ;


    public PostNotificationModel(String ip_adress, String message) {
        this.ip_adress = ip_adress;
        this.message = message;
    }

    public String getIp_adress() {
        return ip_adress;
    }

    public void setIp_adress(String ip_adress) {
        this.ip_adress = ip_adress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
