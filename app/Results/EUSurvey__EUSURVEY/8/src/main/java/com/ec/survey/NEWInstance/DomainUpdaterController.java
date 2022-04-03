package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DomainUpdaterController {

 private DomainUpdater domainupdater;


@PutMapping
("/run")
public void run(){
domainupdater.run();
}


}