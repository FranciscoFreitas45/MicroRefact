package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MenuServiceController {

 private MenuService menuservice;


@GetMapping
("/findSidebarNodesByUserId")
public List<SidebarNode> findSidebarNodesByUserId(@RequestParam(name = "userId") String userId){
  return menuservice.findSidebarNodesByUserId(userId);
}


}