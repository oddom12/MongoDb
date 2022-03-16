package com.example.MongoDb.controller;

import com.example.MongoDb.model.Processes;
import com.example.MongoDb.repository.ProcessesRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ProcessesController {

    private final ProcessesRepository processesRepository ;

    public ProcessesController(ProcessesRepository processesRepository){
        this.processesRepository = processesRepository ;
    }

    @CrossOrigin
    @GetMapping("/networkScan")
    public ResponseEntity<JsonNode> getProcesses() throws IOException {

        ObjectMapper mapper = JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                .build();
        mapper.configure(
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
                true
        );

        StringBuffer sb = new StringBuffer() ;
        StringBuffer sb2 = new StringBuffer() ;
        List<String> processes = new ArrayList<String>();
        String json = null;

        List<String> systeminfos = new ArrayList<String>();

        //get ip and hostname
        Inet4Address inet4Address = (Inet4Address) Inet4Address.getLocalHost();

        //region tasklist
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
            BufferedReader input = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                processes.add(line);


            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        String list = null;
        String jsonProceses = null ;
        for (String process : processes) {
            list =  process.replace("\"","");
            System.out.println(process.replace("\"",""));
            String listed[] = list.split(",") ;
            jsonProceses ="{\"Image_Name\": \""+listed[0]+"\",\"pid\": \""+listed[1]+"\",\"Session_Name\": \""+listed[2]+"\",\"Session\":\""+listed[3]+"\",\"Memory_Usage\":\""+listed[4]+"\"},";
            sb.append(jsonProceses) ;
        }


        String final_process = sb.toString() ;
        if (final_process.length() > 0) {

            final_process = final_process.substring(0, final_process.length() - 1);
            //systeminfo = systeminfo.replaceAll("\\W", ",");
        }

        //endregion








        json = "{\"processes\": ["+final_process+"]}";


        processesRepository.save(new Processes(inet4Address.getHostAddress(),inet4Address.getHostName(),json));

        //,\"systeminfo\":["+final_system+"]
        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(json);
        JsonNode node = mapper.readTree(jsonParser);


        return ResponseEntity.ok(node);
    }

}
