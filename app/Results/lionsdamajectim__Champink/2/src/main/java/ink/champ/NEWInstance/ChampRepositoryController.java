package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChampRepositoryController {

 private ChampRepository champrepository;


@GetMapping
("/findChampsByNameContainingIgnoreCase")
public List<Champ> findChampsByNameContainingIgnoreCase(@RequestParam(name = "search") String search,@RequestParam(name = "sort") Sort sort){
  return champrepository.findChampsByNameContainingIgnoreCase(search,sort);
}


@GetMapping
("/findChampsByUserRoleAndTeamNotIn")
public List<Champ> findChampsByUserRoleAndTeamNotIn(@RequestParam(name = "team") Team team,@RequestParam(name = "user") User user){
  return champrepository.findChampsByUserRoleAndTeamNotIn(team,user);
}


@GetMapping
("/findChampsByPrivatIsFalseAndNameContainingIgnoreCase")
public List<Champ> findChampsByPrivatIsFalseAndNameContainingIgnoreCase(@RequestParam(name = "search") String search,@RequestParam(name = "sort") Sort sort){
  return champrepository.findChampsByPrivatIsFalseAndNameContainingIgnoreCase(search,sort);
}


@GetMapping
("/findChampsByUserAll")
public List<Champ> findChampsByUserAll(@RequestParam(name = "user") User user,@RequestParam(name = "search") String search){
  return champrepository.findChampsByUserAll(user,search);
}


@GetMapping
("/findChampsByUserRole")
public List<Champ> findChampsByUserRole(@RequestParam(name = "user") User user,@RequestParam(name = "role") int role,@RequestParam(name = "search") String search){
  return champrepository.findChampsByUserRole(user,role,search);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return champrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return champrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return champrepository.delete(Object);
}


}