package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrackListController {

 private TrackList tracklist;

 private TrackList tracklist;


@PutMapping
("/setTrackUser")
public void setTrackUser(@RequestParam(name = "trackUser") String trackUser){
tracklist.setTrackUser(trackUser);
}


@PutMapping
("/setTrackContent")
public void setTrackContent(@RequestParam(name = "trackContent") String trackContent){
tracklist.setTrackContent(trackContent);
}


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") String userId){
tracklist.setUserId(userId);
}


}