package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlayerRepositoryController {

 private PlayerRepository playerrepository;


@GetMapping
("/findPlayersByNameContainingIgnoreCase")
public List<Player> findPlayersByNameContainingIgnoreCase(@RequestParam(name = "search") String search,@RequestParam(name = "sort") Sort sort){
  return playerrepository.findPlayersByNameContainingIgnoreCase(search,sort);
}


@GetMapping
("/findPlayersByUserRoleAndNotInTeam")
public List<Player> findPlayersByUserRoleAndNotInTeam(@RequestParam(name = "team") Team team,@RequestParam(name = "user") User user){
  return playerrepository.findPlayersByUserRoleAndNotInTeam(team,user);
}


@GetMapping
("/findPlayersByPrivatIsFalseAndNameContainingIgnoreCase")
public List<Player> findPlayersByPrivatIsFalseAndNameContainingIgnoreCase(@RequestParam(name = "search") String search,@RequestParam(name = "sort") Sort sort){
  return playerrepository.findPlayersByPrivatIsFalseAndNameContainingIgnoreCase(search,sort);
}


@GetMapping
("/findPlayersByUserAll")
public List<Player> findPlayersByUserAll(@RequestParam(name = "user") User user,@RequestParam(name = "search") String search){
  return playerrepository.findPlayersByUserAll(user,search);
}


@GetMapping
("/findPlayersByUserRole")
public List<Player> findPlayersByUserRole(@RequestParam(name = "user") User user,@RequestParam(name = "role") int role,@RequestParam(name = "search") String search){
  return playerrepository.findPlayersByUserRole(user,role,search);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return playerrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return playerrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return playerrepository.delete(Object);
}


}