package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArchiveExecutorController {

 private ArchiveExecutor archiveexecutor;

 private ArchiveExecutor archiveexecutor;


@GetMapping
("/prepare")
public boolean prepare(){
  return archiveexecutor.prepare();
}


}