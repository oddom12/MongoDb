package com.example.MongoDb;

import com.example.MongoDb.model.PostNotificationModel;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@RestController
public  class PostNotification {

    @PostMapping("/test")
    public ResponseEntity<JsonNode> sendNotification() throws IOException{
        ObjectMapper mapper = JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                .build();
        mapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true
        );

        //Get the ip address
        Inet4Address ip = null;
        try {

            ip = (Inet4Address) InetAddress.getLocalHost();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }






        String json = "{\"ip\": \""+ip.getHostAddress()+"\",\"message\": \"I am online\"}" ;

        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(json);
        JsonNode node = mapper.readTree(jsonParser);

        return ResponseEntity.ok(node);
    }

}
