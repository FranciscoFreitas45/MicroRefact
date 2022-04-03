package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FileUpdaterController {

 private FileUpdater fileupdater;


@PutMapping
("/run")
public void run(){
fileupdater.run();
}


}