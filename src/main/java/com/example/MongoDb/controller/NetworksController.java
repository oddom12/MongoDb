package com.example.MongoDb.controller;


import com.example.MongoDb.model.Networks;
import com.example.MongoDb.repository.NetworsRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NetworksController {


    private final NetworsRepository networsRepository ;

    public NetworksController(NetworsRepository networsRepository){
        this.networsRepository = networsRepository ;
    }

    @CrossOrigin
    @GetMapping("/networkIP")
    public ResponseEntity<JsonNode> getProcesses() throws IOException {

        ObjectMapper mapper = JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                .build();
        mapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true
        );

        //create HTTP REQUEST FOR THE SERVER
        //HttpRequest testreq = new HttpRequest();
        //testreq.request() ;


        List<String> processes = new ArrayList<String>();
        String json = null;
        StringBuffer sb = new StringBuffer() ;
        String[] linesArray = null ;
        Inet4Address inet4Address = (Inet4Address) Inet4Address.getLocalHost();

        //region network ip
        try {
            String line;
            Process p = Runtime.getRuntime().exec("netstat");
            BufferedReader input = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);

            }
            linesArray = processes.toArray(new String[processes.size()]);
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        String list = null;
        String jsonProceses = null ;
        for(int i = 4; i < linesArray.length -1 ; i++) {

            list = linesArray[i].trim().replace(" ", ",");
            //System.out.println(linesArray.replace("\"",""));
            list = list.replaceAll(",{2,}", ",");
            String listed[] = list.split(",") ;

            jsonProceses ="{\"Proto\":\""+listed[0]+"\",\"Local_Address\":\""+listed[1]+"\",\"Foreign_Address\":\""+listed[2]+"\",\"State\":\""+listed[3]+"\"},";
            sb.append(jsonProceses) ;
        }

        String final_process = sb.toString() ;
        if (final_process.length() > 0) {

            final_process = final_process.substring(0, final_process.length() - 1);
            //systeminfo = systeminfo.replaceAll("\\W", ",");
        }


        //endregion



        json = "{\"connections\": ["+final_process+"]}";


        networsRepository.save(new Networks(inet4Address.getHostAddress(),inet4Address.getHostName(),json));

        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(json);
        JsonNode node = mapper.readTree(jsonParser);
        return ResponseEntity.ok(node);
    }


}
