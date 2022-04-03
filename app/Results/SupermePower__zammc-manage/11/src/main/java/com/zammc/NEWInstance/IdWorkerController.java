package com.zammc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IdWorkerController {

 private IdWorker idworker;


@GetMapping
("/nextId")
public long nextId(){
  return idworker.nextId();
}


}