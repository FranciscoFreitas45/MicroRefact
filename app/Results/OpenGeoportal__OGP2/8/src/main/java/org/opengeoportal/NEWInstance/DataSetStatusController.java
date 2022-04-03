package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DataSetStatusController {

 private DataSetStatus datasetstatus;

 private DataSetStatus datasetstatus;


@PutMapping
("/setOgpLayerId")
public void setOgpLayerId(@RequestParam(name = "ogpLayerId") String ogpLayerId){
datasetstatus.setOgpLayerId(ogpLayerId);
}


}