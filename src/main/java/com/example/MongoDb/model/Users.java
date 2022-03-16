package com.example.MongoDb.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Users {


    @Id
    private String id ;

    @Field
    private String ip;

    @Field
    private String hostname;

    @Field
    private String users;


    public Users(){}


    public Users(String ip, String users,String hostname) {
        this.ip = ip;
        this.users = users;
        this.hostname = hostname ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return String.format("Users[id='%s',ip='%s',users'%s']",id,ip,users);
    }
}
