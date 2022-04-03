package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UtTimeperiodController {

 private DevinfoUtTimeperiodRepository devinfouttimeperiodrepository;


@PutMapping
("/setStartDate/{id}")
public void setStartDate(@PathVariable(name = "id") int id,@RequestParam(name = "startDate") Date startDate){
 devinfouttimeperiodrepository.setStartDate(id,startDate);
}


@PutMapping
("/setEndDate/{id}")
public void setEndDate(@PathVariable(name = "id") int id,@RequestParam(name = "endDate") Date endDate){
 devinfouttimeperiodrepository.setEndDate(id,endDate);
}


@PutMapping
("/setPeriodicity/{id}")
public void setPeriodicity(@PathVariable(name = "id") int id,@RequestParam(name = "periodicity") String periodicity){
 devinfouttimeperiodrepository.setPeriodicity(id,periodicity);
}


@PutMapping
("/setTimePeriod/{id}")
public void setTimePeriod(@PathVariable(name = "id") int id,@RequestParam(name = "timePeriod") String timePeriod){
 devinfouttimeperiodrepository.setTimePeriod(id,timePeriod);
}


}