package main.service;
 import main.model.request.others.ProfileRequest;
import main.model.request.others.SettingsRequest;
import main.model.request.postids.CommentRequest;
import main.model.request.postids.PostModerationRequest;
import main.model.response.others;
import main.model.response.results.ResultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
public interface GeneralService {


public Blog getBlogInfo()
;

public ResultResponse editProfile(ProfileRequest request)
;

public YearsPostsResponse numberOfPosts(Integer year)
;

public StatisticsResponse myStatistics()
;

public Object allStatistics()
;

public SettingsResponse getSettings()
;

public Object sendComment(CommentRequest request)
;

public ResponseEntity imageUpload(MultipartFile multipartFile)
;

public ResultResponse postModeration(PostModerationRequest request)
;

public ResultResponse editProfileWithPhoto(ProfileRequest request)
;

public void putSettings(SettingsRequest request)
;

public TagsResponse getListOfTags(String query)
;

}