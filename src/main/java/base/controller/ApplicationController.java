package base.controller;

import application.ApplicationOps;
import application.domain.Applications;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping(path = "/")
@ComponentScan("application")
public class ApplicationController {
    /*
    Handling API requests
     */
    @Resource
    ApplicationOps applicationOps;

    @CrossOrigin
    @GetMapping(path = "/", produces = "application/json")
    public HashMap<String, Integer> getApplications(){
        /*
        GET requests
         */
        System.out.println("received GET request");
        HashMap<String, Integer> outputHashMap = new HashMap<>();
        outputHashMap.put("applied", applicationOps.selectByStatus(0).size());
        outputHashMap.put("responsed", applicationOps.selectByStatus(1).size());
        outputHashMap.put("phone", applicationOps.selectByStatus(2).size());
        outputHashMap.put("onsite", applicationOps.selectByStatus(3).size());
        outputHashMap.put("offer", applicationOps.selectByStatus(4).size());
        return outputHashMap;
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addApplication(@RequestBody Applications applications){
        /*
        POST requests: adding an application to the database
         */
        String companyName = applications.getCompanyName();
        String position = applications.getPosition();
        String jobId = applications.getJobId();
        Integer status = applications.getStatus();

        Integer returnStatus = applicationOps.insertApp(companyName, position, jobId, status);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{insertStatus}")
                .buildAndExpand(returnStatus)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
