package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DeleteTemporaryFolderUpdaterController {

 private DeleteTemporaryFolderUpdater deletetemporaryfolderupdater;


@PutMapping
("/run")
public void run(){
deletetemporaryfolderupdater.run();
}


}