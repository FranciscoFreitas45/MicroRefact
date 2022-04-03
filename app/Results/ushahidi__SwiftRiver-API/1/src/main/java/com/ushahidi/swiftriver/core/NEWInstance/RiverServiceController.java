package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverServiceController {

 private RiverService riverservice;


@GetMapping
("/filterVisible")
public List<River> filterVisible(@RequestParam(name = "rivers") List<River> rivers,@RequestParam(name = "queryingAccount") Account queryingAccount){
  return riverservice.filterVisible(rivers,queryingAccount);
}


@GetMapping
("/isOwner")
public boolean isOwner(@RequestParam(name = "river") River river,@RequestParam(name = "account") Account account){
  return riverservice.isOwner(river,account);
}


@GetMapping
("/findRivers")
public List<GetRiverDTO> findRivers(@RequestParam(name = "searchTerm") String searchTerm,@RequestParam(name = "count") int count,@RequestParam(name = "page") int page){
  return riverservice.findRivers(searchTerm,count,page);
}


}