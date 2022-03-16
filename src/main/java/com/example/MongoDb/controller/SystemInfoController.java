package com.example.MongoDb.controller;

import com.example.MongoDb.model.SystemInfo;
import com.example.MongoDb.repository.SystemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SystemInfoController {

    private final SystemRepository systemRepository ;

    @Autowired
    public SystemInfoController(SystemRepository systemRepository){
        this.systemRepository = systemRepository ;
    }

    @CrossOrigin
    @GetMapping("/systeminfo")
    public ResponseEntity<JsonNode> post() throws IOException, JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                .build();


        Inet4Address inet4Address = (Inet4Address) Inet4Address.getLocalHost();
        JsonNode json = null;
        String final_process = null;

        String OSName = System.getProperty("os.name");
        if (OSName.contains("windows") ||OSName.contains("Windows")) {
          System.out.println("hello");
            Runtime rt;
            Process pr;
            BufferedReader in;
            String line = "";

            String fullOSName = "";
            String fullHostName = "" ;
            String fullOSVersion = "" ;
            String fullSystemBoot = "";
            String fullSystemType = "" ;
            String fullTotalMemory = "";
            String fullDomain = "";
            String fullProductID = "" ;
            String fullSystemModel = "";
            String fullSystemDirectory = "" ;
            String fullPage_File_Location = "";
            String fullProcesses = "";
            String fullConfiguration = "";

            final String   OS_Name = "OS Name:";
            final String   Host_Name = "Host Name:" ;
            final String   Bios_Version = "BIOS Version:" ;
            final String   System_Boot_Time= "System Boot Time:" ;
            final String System_Type= "System Type:" ;
            final String TotalPhisicalMemory= "Total Physical Memory:" ;
            final String Domain = "Domain:" ;
            final String ProductID = "Product ID:" ;
            final String SystemModel = "System Model:";
            final String SystemDirectory = "System Directory:";
            final String Page_File_Location = "Page File Location(s):" ;
            final String Processes = "Processor(s):";
            final String Configuration = "OS Configuration:" ;


            try
            {
                rt = Runtime.getRuntime();
                pr = rt.exec("systeminfo");
                in = new BufferedReader(new InputStreamReader(pr.getInputStream()));

                //add all the lines into a variable
                while((line=in.readLine()) != null)
                {
                    if(line.contains(OS_Name)) //found the OS you are using
                    {
                        //extract the full os name
                        fullOSName = line.substring(line.lastIndexOf(OS_Name)
                                + OS_Name.length()).trim();

                    }
                    if(line.contains(Host_Name)) //found the OS you are using
                    {
                        //extract the full os name
                        fullHostName = line.substring(line.lastIndexOf(Host_Name)
                                + Host_Name.length()).trim();
                    }
                    if(line.contains(Bios_Version)) //found the OS you are using
                    {
                        //extract the full os name
                        fullOSVersion = line.substring(line.lastIndexOf(Bios_Version)
                                + Bios_Version.length()).trim();
                    }
                    if(line.contains(System_Boot_Time)) //found the OS you are using
                    {
                        //extract the full os name
                        fullSystemBoot = line.substring(line.lastIndexOf(System_Boot_Time)
                                + System_Boot_Time.length()).trim();
                    }
                    if(line.contains(System_Type)) //found the OS you are using
                    {
                        //extract the full os name
                        fullSystemType = line.substring(line.lastIndexOf(System_Type)
                                + System_Type.length()).trim();
                    }
                    if(line.contains(TotalPhisicalMemory)) //found the OS you are using
                    {
                        //extract the full os name
                        fullTotalMemory = line.substring(line.lastIndexOf(TotalPhisicalMemory)
                                + TotalPhisicalMemory.length()).trim();
                    }
                    if(line.contains(Domain)) //found the OS you are using
                    {
                        //extract the full os name
                        fullDomain = line.substring(line.lastIndexOf(Domain)
                                + Domain.length()).trim();
                    }
                    if(line.contains(ProductID)) //found the OS you are using
                    {
                        //extract the full os name
                        fullProductID = line.substring(line.lastIndexOf(ProductID)
                                + ProductID.length()).trim();
                    }
                    if(line.contains(SystemModel)) //found the OS you are using
                    {
                        //extract the full os name
                        fullSystemModel = line.substring(line.lastIndexOf(SystemModel)
                                + SystemModel.length()).trim();
                    }
                    if(line.contains(SystemDirectory)) //found the OS you are using
                    {
                        //extract the full os name
                        fullSystemDirectory = line.substring(line.lastIndexOf(SystemDirectory)
                                + SystemDirectory.length()).trim();
                    }
                    if(line.contains(Page_File_Location)) //found the OS you are using
                    {
                        //extract the full os name
                        fullPage_File_Location = line.substring(line.lastIndexOf(Page_File_Location)
                                + Page_File_Location.length()).trim();
                    }
                    if(line.contains(Processes)) //found the OS you are using
                    {
                        //extract the full os name
                        fullProcesses = line.substring(line.lastIndexOf(Processes)
                                + Processes.length()).trim();
                    }
                    if(line.contains(Configuration)) //found the OS you are using
                    {
                        //extract the full os name
                        fullConfiguration = line.substring(line.lastIndexOf(Configuration)
                                + Configuration.length()).trim();
                    }

                }


            }
            catch(IOException ioe)
            {
                System.err.println(ioe.getMessage());
            }

            json = mapper.readTree("{\"properties\": [{\"Os_Name\": \""+fullOSName+"\",\"Host_Name\": \""+fullHostName+"\",\"BIOS_Version\": \""+fullOSVersion+"\",\"System_Boot_Time\": \""+fullSystemBoot+"\",\"System_Type\": \""+fullSystemType+"\",\"System_Model\": \""+fullSystemModel+"\",\"Total_Memory\": \""+fullTotalMemory+"\",\"Domain\": \""+fullDomain+"\",\"Product_ID\": \""+fullProductID+"\",\"System_Directory\": \""+fullSystemDirectory+"\",\"Page_File_Location\": \""+fullPage_File_Location+"\",\"Processes\": \""+fullProcesses+"\",\"OS_Configuration\": \""+fullConfiguration+"\"}]}");

            systemRepository.save(new SystemInfo(inet4Address.getHostAddress(),inet4Address.getHostName(),json.toString()));

        }
        else {
            //region linux
            String line;
            Runtime rt;
            Process pr;
            BufferedReader in;
            List<String> processes = new ArrayList<String>();
            StringBuffer sb = new StringBuffer() ;

            try {
                rt = Runtime.getRuntime();
                pr = rt.exec("ps -aux");
                in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                while ((line = in.readLine()) != null) {
                    processes.add(line);
                }
                in.close();

            } catch (Exception err) {
                err.printStackTrace();

            }
            String jsonlinux ;
            String list = null;
            for (String process : processes) {
                list =  process.replace("\"","");
                String listed[] = list.split(",") ;
                jsonlinux = ("{\"processes\" : \""+listed[0]+"\"}") ;
                sb.append(jsonlinux);
            }
             final_process = sb.toString() ;
            if (final_process.length() > 0) {

                final_process = final_process.substring(0, final_process.length() - 1);
                //systeminfo = systeminfo.replaceAll("\\W", ",");
            }

                 json = mapper.readTree(final_process);

            //endregion
        }


        return ResponseEntity.ok(json);
        
    }
}
