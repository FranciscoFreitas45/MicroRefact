package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UpstreamServerController {

 private UpstreamServer upstreamserver;

 private UpstreamServer upstreamserver;


@PutMapping
("/setMonitorStatus")
public void setMonitorStatus(@RequestParam(name = "monitorStatus") Integer monitorStatus){
upstreamserver.setMonitorStatus(monitorStatus);
}


}