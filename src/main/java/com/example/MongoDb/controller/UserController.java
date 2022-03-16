package com.example.MongoDb.controller;

import com.example.MongoDb.model.Users;
import com.example.MongoDb.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository ;
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<JsonNode> postusers() throws JsonProcessingException, UnknownHostException {
        ObjectMapper mapper = JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                .build();


        List<String> lines = new ArrayList<String>();
        String[] linesArray=null ;
        StringBuffer sb = new StringBuffer() ;
        StringBuffer sb2 = new StringBuffer() ;

        Inet4Address inet4Address = (Inet4Address) Inet4Address.getLocalHost();


        try {
            String data;
            Process p2 = Runtime.getRuntime().exec("net user");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p2.getInputStream()));
            while ((data = input.readLine()) != null) {
                System.out.println(data); //<-- Parse data here.
                lines.add(data);

            }
            linesArray = lines.toArray(new String[lines.size()]);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 4; i < linesArray.length -1 ; i++) {
            sb.append(linesArray[i]).append(",");

        }

        String systeminfo  = sb.toString();

        if (systeminfo.length() > 0) {

            systeminfo = systeminfo.substring(0, systeminfo.length() - 1);

            //systeminfo = systeminfo.replaceAll("\\W", ",");
        }

        String users = systeminfo.substring(0,systeminfo.lastIndexOf(',')).trim() ;
        users = users.replace(" ", ",");
        users = users.replaceAll(",{2,}", ",");

        String[] listUsers = users.split(",");

        String jsonUser = null ;




        //take every user for the list
          for (String user: listUsers){
              jsonUser = "{\"user\":\""+user+"\"}," ;
              sb2.append(jsonUser) ;
          }

          //fill the json in the stringbuffer
          String  userinfo = sb2.toString() ;
        if (userinfo.length() > 0) {

            userinfo = userinfo.substring(0, userinfo.length() - 1);
            //systeminfo = systeminfo.replaceAll("\\W", ",");
        }

       // JsonNode json = mapper.readTree("{\"Users\":\""+users+"\"}");

        JsonNode json = mapper.readTree("{\"Users\":["+userinfo+"]}");

        userRepository.save(new Users(inet4Address.getHostAddress(),users,inet4Address.getHostName()));

        return ResponseEntity.ok(json);
    }



}
