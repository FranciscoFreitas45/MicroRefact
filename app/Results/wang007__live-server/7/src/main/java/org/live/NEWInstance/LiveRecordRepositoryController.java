package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LiveRecordRepositoryController {

 private LiveRecordRepository liverecordrepository;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return liverecordrepository.count(Object);
}


@GetMapping
("/find")
public Page<LiveRecordVo> find(@RequestParam(name = "pageRequest") PageRequest pageRequest,@RequestParam(name = "filter") Map<String,Object> filter){
  return liverecordrepository.find(pageRequest,filter);
}


}