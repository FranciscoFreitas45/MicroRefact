package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RestoreExecutorController {

 private RestoreExecutor restoreexecutor;

 private RestoreExecutor restoreexecutor;


@PutMapping
("/prepare")
public void prepare(){
restoreexecutor.prepare();
}


}