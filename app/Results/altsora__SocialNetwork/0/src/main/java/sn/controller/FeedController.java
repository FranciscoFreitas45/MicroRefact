package sn.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.api.response.ServiceResponse;
import sn.service.FeedService;
@RestController
@RequestMapping("/feeds")
public class FeedController {

 private  FeedService feedService;

@Autowired
public FeedController(FeedService feedService) {
    this.feedService = feedService;
}
@GetMapping
public ResponseEntity<ServiceResponse> getFeeds(String name,int offset,int itemPerPage){
    return feedService.getFeeds(name, offset, itemPerPage);
}


}