package com.example.MongoDb.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Networks {

    @Id
    private String id ;

    @Field
    private String ip ;

    @Field
    private String hostname;

    @Field
    private String networks;


    public Networks(){};

    public Networks(String ip, String hostname, String networks) {
        this.ip = ip;
        this.hostname = hostname;
        this.networks = networks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getNetworks() {
        return networks;
    }

    public void setNetworks(String networks) {
        this.networks = networks;
    }
}
