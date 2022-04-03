package main.controller;
 import main.model.request.others.ProfileRequest;
import main.model.request.others.SettingsRequest;
import main.model.request.postids.CommentRequest;
import main.model.request.postids.PostModerationRequest;
import main.model.response.others;
import main.model.response.results.ResultResponse;
import main.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@RestController
@RequestMapping("/api")
public class ApiGeneralController {

@Autowired
 private  GeneralService generalService;


@PostMapping(value = "/profile/my", consumes = "application/json")
public ResultResponse editProfile(ProfileRequest request){
    return generalService.editProfile(request);
}


@GetMapping("/calendar")
public YearsPostsResponse numberOfPosts(Integer year){
    return generalService.numberOfPosts(year);
}


@GetMapping("/statistics/my")
public StatisticsResponse myStatistics(){
    return generalService.myStatistics();
}


@GetMapping("/statistics/all")
public Object allStatistics(){
    return generalService.allStatistics();
}


@GetMapping("/settings")
public SettingsResponse getSettings(){
    return generalService.getSettings();
}


@PostMapping("/comment")
public Object sendComment(CommentRequest request){
    return generalService.sendComment(request);
}


@GetMapping("/init")
public Blog getCommonData(){
    return generalService.getBlogInfo();
}


@PostMapping(value = "/image", consumes = "multipart/form-data")
public ResponseEntity imageUpload(MultipartFile file){
    return generalService.imageUpload(file);
}


@PostMapping("/moderation")
public ResultResponse postModeration(PostModerationRequest request){
    return generalService.postModeration(request);
}


@PostMapping("/profile/my")
public ResultResponse editProfileWithPhoto(ProfileRequest request){
    return generalService.editProfileWithPhoto(request);
}


@PutMapping("/settings")
public void putSettings(SettingsRequest request){
    generalService.putSettings(request);
}


@GetMapping("/tag")
public TagsResponse getListOfTags(String query){
    return generalService.getListOfTags(query);
}


}