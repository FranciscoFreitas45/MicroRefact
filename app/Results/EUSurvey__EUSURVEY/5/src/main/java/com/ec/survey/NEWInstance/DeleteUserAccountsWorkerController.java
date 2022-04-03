package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DeleteUserAccountsWorkerController {

 private DeleteUserAccountsWorker deleteuseraccountsworker;


@PutMapping
("/run")
public void run(){
deleteuseraccountsworker.run();
}


}