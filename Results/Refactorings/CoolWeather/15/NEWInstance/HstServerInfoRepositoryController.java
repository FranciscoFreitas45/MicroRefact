import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class HstServerInfoRepositoryController {

 private HstServerInfoRepository hstserverinforepository;


@GetMapping
("/findByNodeId")
public List<HstServer> findByNodeId(@RequestParam(name = "nodeId") String nodeId,@RequestParam(name = "hostSlaveFlag") int hostSlaveFlag){
  return hstserverinforepository.findByNodeId(nodeId,hostSlaveFlag);
}


@GetMapping
("/findByNodeId")
public List<HstServer> findByNodeId(@RequestParam(name = "nodeId") String nodeId,@RequestParam(name = "hostSlaveFlag") int hostSlaveFlag){
  return hstserverinforepository.findByNodeId(nodeId,hostSlaveFlag);
}


@GetMapping
("/findByNodeId")
public List<HstServer> findByNodeId(@RequestParam(name = "nodeId") String nodeId,@RequestParam(name = "hostSlaveFlag") int hostSlaveFlag){
  return hstserverinforepository.findByNodeId(nodeId,hostSlaveFlag);
}


@GetMapping
("/findByNodeId")
public List<HstServer> findByNodeId(@RequestParam(name = "nodeId") String nodeId,@RequestParam(name = "hostSlaveFlag") int hostSlaveFlag){
  return hstserverinforepository.findByNodeId(nodeId,hostSlaveFlag);
}


@GetMapping
("/findHostServer")
public List<HstServer> findHostServer(@RequestParam(name = "hostSlaveFlag") int hostSlaveFlag){
  return hstserverinforepository.findHostServer(hostSlaveFlag);
}


@GetMapping
("/findHostServer")
public List<HstServer> findHostServer(@RequestParam(name = "hostSlaveFlag") int hostSlaveFlag){
  return hstserverinforepository.findHostServer(hostSlaveFlag);
}


}