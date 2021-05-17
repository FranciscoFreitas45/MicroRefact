import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class HostOrSlaveRequestResourceController {

 private HostOrSlaveRequestResource hostorslaverequestresource;


@GetMapping
("/saveInfomation")
public String saveInfomation(@RequestParam(name = "hstServers") List<HstServer> hstServers,@RequestParam(name = "localUrl") String localUrl,@RequestParam(name = "localNode") String localNode,@RequestParam(name = "hostNodeInfo") String hostNodeInfo,@RequestParam(name = "postHstServers") List<HstServer> postHstServers){
  return hostorslaverequestresource.saveInfomation(hstServers,localUrl,localNode,hostNodeInfo,postHstServers);
}


@GetMapping
("/saveInfomation")
public String saveInfomation(@RequestParam(name = "hstServers") List<HstServer> hstServers,@RequestParam(name = "localUrl") String localUrl,@RequestParam(name = "localNode") String localNode,@RequestParam(name = "hostNodeInfo") String hostNodeInfo,@RequestParam(name = "postHstServers") List<HstServer> postHstServers){
  return hostorslaverequestresource.saveInfomation(hstServers,localUrl,localNode,hostNodeInfo,postHstServers);
}


@GetMapping
("/saveInfomation")
public String saveInfomation(@RequestParam(name = "hstServers") List<HstServer> hstServers,@RequestParam(name = "localUrl") String localUrl,@RequestParam(name = "localNode") String localNode,@RequestParam(name = "hostNodeInfo") String hostNodeInfo,@RequestParam(name = "postHstServers") List<HstServer> postHstServers){
  return hostorslaverequestresource.saveInfomation(hstServers,localUrl,localNode,hostNodeInfo,postHstServers);
}


}