package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JobDetailRepositoryController {

 private JobDetailRepository jobdetailrepository;


@GetMapping
("/findByTaskstatus")
public Page<JobDetail> findByTaskstatus(@RequestParam(name = "taskstatus") String taskstatus,@RequestParam(name = "page") Pageable page){
  return jobdetailrepository.findByTaskstatus(taskstatus,page);
}


@GetMapping
("/findByPlantaskAndTaskstatusAndNextfiretimeLessThan")
public Page<JobDetail> findByPlantaskAndTaskstatusAndNextfiretimeLessThan(@RequestParam(name = "plantask") boolean plantask,@RequestParam(name = "taskstatus") String taskstatus,@RequestParam(name = "time") Date time,@RequestParam(name = "page") Pageable page){
  return jobdetailrepository.findByPlantaskAndTaskstatusAndNextfiretimeLessThan(plantask,taskstatus,time,page);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return jobdetailrepository.save(Object);
}


}