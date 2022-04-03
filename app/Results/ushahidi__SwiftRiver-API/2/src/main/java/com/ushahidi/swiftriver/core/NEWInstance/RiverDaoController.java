package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RiverDaoController {

 private RiverDao riverdao;


@GetMapping
("/findByName")
public River findByName(@RequestParam(name = "name") String name){
  return riverdao.findByName(name);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return riverdao.create(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return riverdao.update(Object);
}


@GetMapping
("/getDrops")
public List<Drop> getDrops(@RequestParam(name = "riverId") Long riverId,@RequestParam(name = "filter") DropFilter filter,@RequestParam(name = "page") int page,@RequestParam(name = "dropCount") int dropCount,@RequestParam(name = "queryingAccount") Account queryingAccount){
  return riverdao.getDrops(riverId,filter,page,dropCount,queryingAccount);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return riverdao.delete(Object);
}


@GetMapping
("/findCollaborator")
public RiverCollaborator findCollaborator(@RequestParam(name = "riverId") Long riverId,@RequestParam(name = "accountId") Long accountId){
  return riverdao.findCollaborator(riverId,accountId);
}


@GetMapping
("/addCollaborator")
public RiverCollaborator addCollaborator(@RequestParam(name = "river") River river,@RequestParam(name = "account") Account account,@RequestParam(name = "readOnly") boolean readOnly){
  return riverdao.addCollaborator(river,account,readOnly);
}


@PutMapping
("/updateCollaborator")
public void updateCollaborator(@RequestParam(name = "collaborator") RiverCollaborator collaborator){
riverdao.updateCollaborator(collaborator);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return riverdao.findById(Object);
}


@GetMapping
("/findRiverDrop")
public RiverDrop findRiverDrop(@RequestParam(name = "id") Long id,@RequestParam(name = "dropId") Long dropId){
  return riverdao.findRiverDrop(id,dropId);
}


@GetMapping
("/findAll")
public List<River> findAll(@RequestParam(name = "searchTerm") String searchTerm,@RequestParam(name = "count") int count,@RequestParam(name = "page") int page){
  return riverdao.findAll(searchTerm,count,page);
}


@GetMapping
("/getTrendingTags")
public List<RiverTagTrend> getTrendingTags(@RequestParam(name = "riverId") Long riverId,@RequestParam(name = "trendFilter") TrendFilter trendFilter){
  return riverdao.getTrendingTags(riverId,trendFilter);
}


@GetMapping
("/getTrendingPlaces")
public List<RiverTagTrend> getTrendingPlaces(@RequestParam(name = "riverId") Long riverId,@RequestParam(name = "trendFilter") TrendFilter trendFilter){
  return riverdao.getTrendingPlaces(riverId,trendFilter);
}


}