package com.example.MongoDb.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class SystemInfo {

    @Id
    private String id ;

    @Field
    private String ip ;

    @Field
    private String hostname ;

    @Field
    private String systeminfo ;


    public SystemInfo(){}


    public SystemInfo(String ip, String hostname, String systeminfo) {
        this.ip = ip;
        this.hostname = hostname;
        this.systeminfo = systeminfo;
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

    public String getSysteminfo() {
        return systeminfo;
    }

    public void setSysteminfo(String systeminfo) {
        this.systeminfo = systeminfo;
    }
}
