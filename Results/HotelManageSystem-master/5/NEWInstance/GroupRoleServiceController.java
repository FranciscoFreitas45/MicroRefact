import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GroupRoleServiceController {

 private GroupRoleService grouproleservice;


@GetMapping
("/findByGroupName")
public GroupRole findByGroupName(@RequestParam(name = "groupName") String groupName){
  return grouproleservice.findByGroupName(groupName);
}


}