package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SpaceServiceController {

 private SpaceService spaceservice;


@GetMapping
("/getSpaceInfo")
public SpaceInfoVo getSpaceInfo(@RequestParam(name = "spaceIdx") int spaceIdx){
  return spaceservice.getSpaceInfo(spaceIdx);
}


@GetMapping
("/getRecentSpaceList")
public List<Map<String,String>> getRecentSpaceList(@RequestParam(name = "weUserIdx") Integer weUserIdx,@RequestParam(name = "startRow") Integer startRow,@RequestParam(name = "endRow") Integer endRow){
  return spaceservice.getRecentSpaceList(weUserIdx,startRow,endRow);
}


}