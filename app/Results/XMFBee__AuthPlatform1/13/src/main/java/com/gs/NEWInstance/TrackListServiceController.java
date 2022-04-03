package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrackListServiceController {

 private TrackListService tracklistservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return tracklistservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return tracklistservice.queryByPager(Object);
}


@GetMapping
("/countName")
public int countName(@RequestParam(name = "trackList") TrackList trackList,@RequestParam(name = "user") User user){
  return tracklistservice.countName(trackList,user);
}


@GetMapping
("/queryByPagerName")
public List<TrackList> queryByPagerName(@RequestParam(name = "pager") Pager pager,@RequestParam(name = "trackList") TrackList trackList){
  return tracklistservice.queryByPagerName(pager,trackList);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return tracklistservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return tracklistservice.update(Object);
}


}