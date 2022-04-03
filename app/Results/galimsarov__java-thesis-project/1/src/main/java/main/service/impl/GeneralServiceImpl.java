package main.service.impl;
 import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import main.config.AuthConfiguration;
import main.model;
import main.model.helper.PostStatus;
import main.model.response.results.Error;
import main.repository;
import main.model.request.others.ProfileRequest;
import main.model.request.others.SettingsRequest;
import main.model.request.postids.CommentRequest;
import main.model.request.postids.PostModerationRequest;
import main.model.response.ids.IdResponse;
import main.model.response.others;
import main.model.response.passwords.EmailNamePhotoResp;
import main.model.response.results.ResultResponse;
import main.service.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.persistence;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util;
import main.Interface.PostRepository;
import main.Interface.UserRepository;
import main.Interface.AuthConfiguration;
import main.Interface.TagRepository;
import main.DTO.Post;
@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService{

 private  Blog blog;

 private  Cloudinary cloudinary;

 private  HttpServletRequest request;

 private  PostRepository postRepository;

 private  UserRepository userRepository;

 private  PostCommentRepository commentRepository;

 private  GlobalSettingsRepository globalSettingsRepository;

 private  AuthConfiguration authConfiguration;

 private  Tag2PostRepository tag2PostRepository;

 private  TagRepository tagRepository;

@PersistenceContext
 private  EntityManager entityManager;


@Override
public Blog getBlogInfo(){
    return blog;
}


public EmailNamePhotoResp checkErrors(UserRepository userRepository,ProfileRequest request,User user){
    EmailNamePhotoResp errors = new EmailNamePhotoResp();
    if ((userRepository.findByEmail(request.getEmail()) != null) && !user.getEmail().equals(request.getEmail()))
        errors.setEmail("Этот e-mail уже зарегистрирован");
    if (request.getName() == null)
        errors.setName("Имя указано неверно");
    if (request.getPassword() != null)
        if (request.getPassword().length() < 6)
            errors.setPassword("Пароль короче 6-ти символов");
    if (request.getPhoto() != null)
        if (request.getPhoto().getSize() > 10485760)
            errors.setPhoto("Фото слишком большое, нужно не более 10 Мб");
    return errors;
}


@Override
public YearsPostsResponse numberOfPosts(Integer year){
    List<Post> postList = postRepository.findAll();
    Set<Integer> years = new TreeSet<>();
    for (Post post : postList) {
        int currentYear = Integer.parseInt(post.getTime().toString().substring(0, 4));
        years.add(currentYear);
    }
    String parameter;
    if (year == null) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        parameter = String.valueOf(currentYear);
    } else
        parameter = String.valueOf(year);
    List<String> allDays = postRepository.getPostsForTheYear(parameter);
    Map<String, Integer> posts = new HashMap<>();
    for (String day : allDays) {
        int value;
        if (posts.containsKey(day))
            value = posts.get(day) + 1;
        else
            value = 1;
        posts.put(day, value);
    }
    YearsPostsResponse response = new YearsPostsResponse();
    response.setYears(years);
    response.setPosts(posts);
    return response;
}


@Override
public StatisticsResponse myStatistics(){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int userId = authConfiguration.getAuths().get(currentSession);
    StatisticsResponse response = new StatisticsResponse();
    int postsCount = postRepository.getPostsCountOfUser(userId);
    if (postsCount == 0) {
        response.setPostsCount(0);
        response.setLikesCount(0);
        response.setDislikesCount(0);
        response.setViewsCount(0);
        response.setFirstPublication(0);
    } else {
        response.setPostsCount(postsCount);
        response.setLikesCount(postRepository.getLikesCountOfUsersPosts(userId));
        response.setDislikesCount(postRepository.getDisLikesCountOfUsersPosts(userId));
        response.setViewsCount(postRepository.getViewsCountOfUsersPosts(userId));
        response.setFirstPublication(postRepository.getFirstPostOfUser(userId).getTime() / 1000);
    }
    return response;
}


@Override
public Object allStatistics(){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int userId = authConfiguration.getAuths().get(currentSession);
    if ((userRepository.isAdmin(userId) == 0) && (globalSettingsRepository.statisticsIsPublic().equals("NO")))
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    else {
        StatisticsResponse response = new StatisticsResponse();
        response.setPostsCount(postRepository.getPostsCount());
        response.setLikesCount(postRepository.getLikesCount());
        response.setDislikesCount(postRepository.getDisLikesCount());
        response.setViewsCount(postRepository.getViewsCount());
        response.setFirstPublication(postRepository.getFirstPost().getTime() / 1000);
        return response;
    }
}


@Override
public SettingsResponse getSettings(){
    SettingsResponse response = new SettingsResponse();
    List<GlobalSetting> settings = globalSettingsRepository.findAll();
    for (GlobalSetting setting : settings) {
        if (setting.getCode().equals("MULTIUSER_MODE"))
            response.setMultiUserMode(setting.getValue().equals("YES"));
        if (setting.getCode().equals("POST_PREMODERATION"))
            response.setPostPreModeration(setting.getValue().equals("YES"));
        if (setting.getCode().equals("STATISTICS_IS_PUBLIC"))
            response.setStatisticsIsPublic(setting.getValue().equals("YES"));
    }
    return response;
}


@Override
public Object sendComment(CommentRequest request){
    if (request.getText().length() < 3) {
        Error response = new Error();
        TextResponse errors = new TextResponse();
        if (request.getText().length() == 0)
            errors.setText("Комментарий не установлен");
        else
            errors.setText("Текст комментария слишком короткий");
        response.setResult(false);
        response.setErrors(errors);
        return response;
    }
    IdResponse response = new IdResponse();
    try {
        Post post = postRepository.getOne(request.getPostId());
        PostComment comment = new PostComment();
        PostComment parentComment = commentRepository.getOne(request.getParent_id());
        if (parentComment.getId() != 0) {
            String tempText = parentComment.getText();
        }
        String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
        int id = authConfiguration.getAuths().get(currentSession);
        User user = userRepository.getOne(id);
        comment.setParentId(request.getParent_id());
        comment.setPost(post);
        comment.setText(request.getText());
        comment.setTime(new Date());
        comment.setUser(user);
        post.addPostComment(comment);
        postRepository.saveAndFlush(post);
        response.setId(commentRepository.findIdByTime(comment.getTime()));
    } catch (EntityNotFoundException e) {
        return response;
    }
    return response;
}


public User getUser(AuthConfiguration authConfiguration,UserRepository userRepository){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int id = authConfiguration.getAuths().get(currentSession);
    return userRepository.findById(id).get();
}


@Override
public ResponseEntity imageUpload(MultipartFile file){
    String response = null;
    ImageResponse errors = new ImageResponse();
    if (!file.getOriginalFilename().endsWith("jpg") && !file.getOriginalFilename().endsWith("png"))
        errors.setImage("Файл не является изображением");
    if (file.getSize() > 1048576)
        errors.setImage("Размер файла превышает допустимый размер");
    if (errors.getImage() != null) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    if (!file.isEmpty()) {
        String realPathToUploads = request.getServletContext().getRealPath("") + file.getOriginalFilename();
        File dest = new File(realPathToUploads);
        file.transferTo(dest);
        Map uploadResult = cloudinary.uploader().upload(dest, ObjectUtils.emptyMap());
        response = uploadResult.get("url").toString();
    }
    return ResponseEntity.ok(response);
}


@Override
public ResultResponse postModeration(PostModerationRequest request){
    ResultResponse response = new ResultResponse();
    response.setResult(false);
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int id = authConfiguration.getAuths().get(currentSession);
    User user = userRepository.findById(id).get();
    if (user.isModerator()) {
        int postId = request.getPostId();
        Post post = postRepository.getOne(postId);
        String decision = request.getDecision();
        if (decision.equals("accept"))
            post.setModerationStatus(PostStatus.ACCEPTED);
        else if (decision.equals("decline"))
            post.setModerationStatus(PostStatus.DECLINED);
        else
            return response;
        int moderatorId = user.getId();
        post.setModeratorId(moderatorId);
        postRepository.saveAndFlush(post);
        response.setResult(true);
        return response;
    }
    return response;
}


@Override
public void putSettings(SettingsRequest request){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int userId = authConfiguration.getAuths().get(currentSession);
    if (userRepository.isAdmin(userId) == 1) {
        List<GlobalSetting> settings = globalSettingsRepository.findAll();
        for (GlobalSetting globalSetting : settings) {
            if (globalSetting.getCode().equals("MULTIUSER_MODE"))
                if (request.isMultiUserMode())
                    globalSetting.setValue("YES");
                else
                    globalSetting.setValue("NO");
            if (globalSetting.getCode().equals("POST_PREMODERATION"))
                if (request.isPostPreModeration())
                    globalSetting.setValue("YES");
                else
                    globalSetting.setValue("NO");
            if (globalSetting.getCode().equals("STATISTICS_IS_PUBLIC"))
                if (request.isStatisticsIsPublic())
                    globalSetting.setValue("YES");
                else
                    globalSetting.setValue("NO");
        }
        globalSettingsRepository.saveAll(settings);
    }
}


@Override
public ResultResponse editProfile(ProfileRequest request){
    User user = getUser(authConfiguration, userRepository);
    EmailNamePhotoResp errors = checkErrors(userRepository, request, user);
    if ((errors.getEmail() != null) || (errors.getName() != null) || (errors.getPassword() != null)) {
        Error response = new Error();
        response.setErrors(errors);
        return response;
    } else {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null)
            user.setPassword(request.getPassword());
        if (request.getRemovePhoto() == 1)
            user.setPhoto(null);
        userRepository.saveAndFlush(user);
        ResultResponse response = new ResultResponse();
        response.setResult(true);
        return response;
    }
}


@Override
public ResultResponse editProfileWithPhoto(ProfileRequest requestWithPhoto){
    User user = getUser(authConfiguration, userRepository);
    EmailNamePhotoResp errors = checkErrors(userRepository, requestWithPhoto, user);
    if ((errors.getEmail() != null) || (errors.getName() != null) || (errors.getPassword() != null) || (errors.getPhoto() != null)) {
        Error response = new Error();
        response.setErrors(errors);
        return response;
    } else {
        user.setName(requestWithPhoto.getName());
        user.setEmail(requestWithPhoto.getEmail());
        if (requestWithPhoto.getPassword() != null)
            user.setPassword(requestWithPhoto.getPassword());
        String realPathToUploads = request.getServletContext().getRealPath("");
        String orgName = requestWithPhoto.getPhoto().getOriginalFilename();
        String filePath = realPathToUploads + orgName;
        File dest = new File(filePath);
        requestWithPhoto.getPhoto().transferTo(dest);
        BufferedImage image = ImageIO.read(dest);
        BufferedImage newImage = new BufferedImage(36, 36, BufferedImage.TYPE_INT_RGB);
        int startX = 0, startY = 0, step;
        if (image.getWidth() > image.getHeight()) {
            startX = image.getWidth() / 2 - image.getHeight() / 2;
            step = image.getHeight() / 36;
        } else {
            startY = image.getHeight() / 2 - image.getWidth() / 2;
            step = image.getWidth() / 36;
        }
        for (int x = 0; x < 36; x++) for (int y = 0; y < 36; y++) {
            int rgb = image.getRGB(x * step + startX, y * step + startY);
            newImage.setRGB(x, y, rgb);
        }
        File newFile = new File(realPathToUploads + "user" + user.getId() + "Ava.jpg");
        ImageIO.write(newImage, "jpg", newFile);
        Map uploadResult = cloudinary.uploader().upload(newFile, ObjectUtils.emptyMap());
        user.setPhoto(uploadResult.get("url").toString());
        userRepository.saveAndFlush(user);
        ResultResponse response = new ResultResponse();
        response.setResult(true);
        return response;
    }
}


@Override
public TagsResponse getListOfTags(String query){
    List<String> tagList;
    if (query == null)
        tagList = tagRepository.findNamesOfTags();
    else
        tagList = tagRepository.findByName(query);
    List<TagToPost> tagToPostList = tag2PostRepository.findActiveTagToPosts();
    Map<String, Float> fullMap = new HashMap<>();
    for (TagToPost tagToPost : tagToPostList) {
        String key = tagRepository.getOne(tagToPost.getTagId()).getName();
        float newValue;
        if (fullMap.containsKey(key))
            newValue = fullMap.get(key) + 1;
        else
            newValue = 1;
        fullMap.put(key, newValue);
    }
    float maxWeigh = 0;
    List<NameWeightResp> tags = new ArrayList<>();
    for (String key : fullMap.keySet()) {
        float value = fullMap.get(key) / fullMap.size();
        if (value > maxWeigh)
            maxWeigh = value;
        if (tagList.contains(key)) {
            NameWeightResp tag = new NameWeightResp();
            tag.setName(key);
            tag.setWeight(value);
            tags.add(tag);
        }
    }
    for (NameWeightResp current : tags) {
        float newWeight = current.getWeight() / maxWeigh;
        current.setWeight(newWeight);
    }
    TagsResponse response = new TagsResponse();
    response.setTags(tags);
    return response;
}


}