package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeamRepositoryController {

 private TeamRepository teamrepository;


@GetMapping
("/findTeamsByNameContainingIgnoreCase")
public List<Team> findTeamsByNameContainingIgnoreCase(@RequestParam(name = "search") String search,@RequestParam(name = "sort") Sort sort){
  return teamrepository.findTeamsByNameContainingIgnoreCase(search,sort);
}


@GetMapping
("/findTeamsByUserRoleAndNotInChamp")
public List<Team> findTeamsByUserRoleAndNotInChamp(@RequestParam(name = "champ") Champ champ,@RequestParam(name = "user") User user){
  return teamrepository.findTeamsByUserRoleAndNotInChamp(champ,user);
}


@GetMapping
("/findTeamsByUserRoleAndPlayerNotIn")
public List<Team> findTeamsByUserRoleAndPlayerNotIn(@RequestParam(name = "player") Player player,@RequestParam(name = "user") User user){
  return teamrepository.findTeamsByUserRoleAndPlayerNotIn(player,user);
}


@GetMapping
("/findTeamsByPrivatIsFalseAndNameContainingIgnoreCase")
public List<Team> findTeamsByPrivatIsFalseAndNameContainingIgnoreCase(@RequestParam(name = "search") String search,@RequestParam(name = "sort") Sort sort){
  return teamrepository.findTeamsByPrivatIsFalseAndNameContainingIgnoreCase(search,sort);
}


@GetMapping
("/findTeamsByUserAll")
public List<Team> findTeamsByUserAll(@RequestParam(name = "user") User user,@RequestParam(name = "search") String search){
  return teamrepository.findTeamsByUserAll(user,search);
}


@GetMapping
("/findTeamsByUserRole")
public List<Team> findTeamsByUserRole(@RequestParam(name = "user") User user,@RequestParam(name = "role") int role,@RequestParam(name = "search") String search){
  return teamrepository.findTeamsByUserRole(user,role,search);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return teamrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return teamrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return teamrepository.delete(Object);
}


}