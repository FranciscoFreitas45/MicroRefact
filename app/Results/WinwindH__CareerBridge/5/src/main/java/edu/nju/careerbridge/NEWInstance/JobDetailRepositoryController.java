package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JobDetailRepositoryController {

 private JobDetailRepository jobdetailrepository;


@GetMapping
("/findByJobId")
public JobDetail findByJobId(@RequestParam(name = "jobId") String jobId){
  return jobdetailrepository.findByJobId(jobId);
}


}