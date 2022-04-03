package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OneTimeTokenServiceController {

 private OneTimeTokenService onetimetokenservice;


@GetMapping
("/get")
public Optional<String> get(@RequestParam(name = "oneTimeToken") String oneTimeToken){
  return onetimetokenservice.get(oneTimeToken);
}


@PutMapping
("/revoke")
public void revoke(@RequestParam(name = "oneTimeToken") String oneTimeToken){
onetimetokenservice.revoke(oneTimeToken);
}


}