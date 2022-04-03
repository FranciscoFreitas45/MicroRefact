package main.service.impl;
 import lombok.RequiredArgsConstructor;
import main.config.AuthConfiguration;
import main.model.response.results.Error;
import main.service.PostResponseMapper;
import main.model.Post;
import main.model.PostVote;
import main.model.Tag;
import main.model.User;
import main.model.helper.PostStatus;
import main.repository;
import main.model.request.others.PostRequest;
import main.model.request.postids.PostIdRequest;
import main.model.response.ids.PostPreview;
import main.model.response.others.ThePosts;
import main.model.response.others.TitleTextResponse;
import main.model.response.results.ResultResponse;
import main.service.PostService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import javax.persistence.EntityNotFoundException;
import java.util;
import main.Interface.UserRepository;
import main.Interface.GlobalSettingsRepository;
import main.Interface.AuthConfiguration;
import main.DTO.PostResponseMapper;
import main.DTO.PostResponseMapper;
import main.DTO.PostResponseMapper;
import main.DTO.PostResponseMapper;
import main.DTO.PostResponseMapper;
import main.DTO.PostResponseMapper;
import main.DTO.PostResponseMapper;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

 private  PostRepository postRepository;

 private  UserRepository userRepository;

 private  TagRepository tagRepository;

 private  PostVoteRepository postVoteRepository;

 private  GlobalSettingsRepository globalSettingsRepository;

 private  AuthConfiguration authConfiguration;


@Override
public ResultResponse like(PostIdRequest request){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    ResultResponse response = new ResultResponse();
    response.setResult(false);
    if (authConfiguration.getAuths().get(currentSession) != null) {
        int userId = authConfiguration.getAuths().get(currentSession);
        PostVote postVote = postVoteRepository.getPostVoteByPostAndUser(request.getPostId(), userId);
        if (postVote == null) {
            PostVote newPostVote = new PostVote();
            newPostVote.setPost(postRepository.getPost(request.getPostId()));
            newPostVote.setTime(new Date());
            newPostVote.setUser(userRepository.getOne(userId));
            newPostVote.setValue(1);
            postVoteRepository.saveAndFlush(newPostVote);
            response.setResult(true);
        } else {
            if (postVote.getValue() == 1)
                response.setResult(false);
            else {
                postVote.setValue(1);
                postVote.setTime(new Date());
                postVoteRepository.saveAndFlush(postVote);
                response.setResult(true);
            }
        }
    }
    return response;
}


public Post createNewPost(PostRequest request,Post post){
    post.setActive(request.getActive() == 1);
    post.setTime(new Date(request.getTimestamp()));
    post.setTitle(request.getTitle());
    post.setText(request.getText());
    List<String> namesOfCurrentTags = tagRepository.findNamesOfTags();
    Set<Tag> newTags = new HashSet<>();
    for (String nameOfTagFromRequest : request.getTags()) {
        Tag newTag = new Tag();
        if (namesOfCurrentTags.contains(nameOfTagFromRequest))
            newTag = tagRepository.findTagByName(nameOfTagFromRequest);
        else
            newTag.setName(nameOfTagFromRequest);
        newTags.add(newTag);
    }
    post.setTagSet(newTags);
    return post;
}


@Override
public ResultResponse dislike(PostIdRequest request){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    ResultResponse response = new ResultResponse();
    response.setResult(false);
    if (authConfiguration.getAuths().get(currentSession) != null) {
        int userId = authConfiguration.getAuths().get(currentSession);
        PostVote postVote = postVoteRepository.getPostVoteByPostAndUser(request.getPostId(), userId);
        if (postVote == null) {
            PostVote newPostVote = new PostVote();
            newPostVote.setPost(postRepository.getPost(request.getPostId()));
            newPostVote.setTime(new Date());
            newPostVote.setUser(userRepository.getOne(userId));
            newPostVote.setValue(-1);
            postVoteRepository.saveAndFlush(newPostVote);
            response.setResult(true);
        } else {
            if (postVote.getValue() == -1)
                response.setResult(false);
            else {
                postVote.setValue(-1);
                postVote.setTime(new Date());
                postVoteRepository.saveAndFlush(postVote);
                response.setResult(true);
            }
        }
    }
    return response;
}


@Override
public ThePosts getListOfPostResponse(int offset,int limit,String mode){
    List<PostPreview> posts = new ArrayList<>();
    PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
    Pageable pageable = PageRequest.of(offset / 10, limit);
    Page<Post> postPage;
    switch(mode) {
        case "best":
            postPage = postRepository.findPostsSortedByLikesCount(pageable);
            break;
        case "popular":
            postPage = postRepository.findPostsSortedByCommentsCount(pageable);
            break;
        case "early":
            postPage = postRepository.findEarlyPosts(pageable);
            break;
        default:
            postPage = postRepository.findRecentPosts(pageable);
    }
    for (Post post : postPage) {
        PostPreview postForFeed = mapper.postToPostPreview(post);
        posts.add(postForFeed);
    }
    ThePosts response = new ThePosts();
    response.setCount(postRepository.findCountOfPosts());
    response.setPosts(posts);
    return response;
}


@Override
public Object addPost(PostRequest request){
    Error error = checkPostData(request);
    if (!error.isResult())
        return error;
    if (request.getTimestamp() < (new Date()).getTime())
        request.setTimestamp((new Date()).getTime());
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int id = authConfiguration.getAuths().get(currentSession);
    Post post = new Post();
    post.setUser(userRepository.getOne(id));
    createNewPost(request, post);
    if (globalSettingsRepository.postPremoderation().equals("YES"))
        post.setModerationStatus(PostStatus.NEW);
    else {
        post.setModerationStatus(PostStatus.ACCEPTED);
        post.setActive(true);
    }
    postRepository.saveAndFlush(post);
    ResultResponse response = new ResultResponse();
    response.setResult(true);
    return response;
}


@Override
public ThePosts searchForPostResponse(int offset,int limit,String query){
    ThePosts response = new ThePosts();
    List<PostPreview> posts = new ArrayList<>();
    PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
    Pageable pageable = PageRequest.of(offset / 10, limit);
    List<Post> postList = postRepository.searchForPostsByQuery(query, pageable);
    for (Post post : postList) {
        PostPreview postDTO = mapper.postToPostPreview(post);
        posts.add(postDTO);
    }
    response.setCount(postRepository.getCountOfPostsByQuery(query));
    response.setPosts(posts);
    return response;
}


@Override
public Object editPost(int id,PostRequest request){
    Error error = checkPostData(request);
    if (!error.isResult())
        return error;
    if (request.getTimestamp() < (new Date()).getTime())
        request.setTimestamp((new Date()).getTime());
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int userIdFromContext = authConfiguration.getAuths().get(currentSession);
    User user = userRepository.getOne(userIdFromContext);
    Post post = postRepository.getOne(id);
    createNewPost(request, post);
    if (user.getId() == post.getUser().getId())
        post.setModerationStatus(PostStatus.NEW);
    postRepository.saveAndFlush(post);
    ResultResponse response = new ResultResponse();
    response.setResult(true);
    return response;
}


@Override
public ThePosts getPostsForModeration(int offset,int limit,String status){
    ThePosts response = new ThePosts();
    List<PostPreview> posts = new ArrayList<>();
    PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
    Pageable pageable = PageRequest.of(offset / 10, limit);
    List<Post> postList;
    int count;
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int id = authConfiguration.getAuths().get(currentSession);
    switch(status) {
        case "new":
            postList = postRepository.getNewPosts(id, pageable);
            count = postRepository.getCountOfNewPosts(id);
            break;
        case "declined":
            postList = postRepository.getDeclinedPosts(id, pageable);
            count = postRepository.getCountOfDeclinedPosts(id);
            break;
        default:
            postList = postRepository.getAcceptedPosts(id, pageable);
            count = postRepository.getCountOfAcceptedPosts(id);
    }
    for (Post post : postList) {
        PostPreview postResponse = mapper.postToPostPreview(post);
        posts.add(postResponse);
    }
    response.setCount(count);
    response.setPosts(posts);
    return response;
}


@Override
public Object getPost(int id){
    try {
        Post post = postRepository.getOne(id);
        PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
        post.setViewCount(checkViewCount(post));
        postRepository.saveAndFlush(post);
        return mapper.postToPostResponse(post);
    } catch (EntityNotFoundException e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}


public String dateAfter(String date){
    return date.substring(0, 8) + (Integer.parseInt(date.substring(8, 10)) + 1) + " 00:00:00.000000";
}


public String dateBefore(String date){
    return date + " 00:00:00.000000";
}


public Error checkPostData(PostRequest request){
    Error response = new Error();
    response.setResult(true);
    TitleTextResponse errors = new TitleTextResponse();
    if ((request.getTitle().length() == 0) || (request.getTitle() == null)) {
        response.setResult(false);
        errors.setTitle("Заголовок не установлен");
    } else if (request.getTitle().length() < 3) {
        response.setResult(false);
        errors.setTitle("Текст заголовка слишком короткий");
    }
    if (request.getText().length() == 0) {
        response.setResult(false);
        errors.setText("Публикация не установлена");
    } else if (request.getText().length() < 50) {
        response.setResult(false);
        errors.setText("Текст публикации слишком короткий");
    }
    response.setErrors(errors);
    return response;
}


@Override
public ThePosts getPostsByTag(int offset,int limit,String tag){
    ThePosts response = new ThePosts();
    List<PostPreview> posts = new ArrayList<>();
    PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
    Pageable pageable = PageRequest.of(offset / 10, limit);
    List<Post> postList = postRepository.getPostsByTag(tag, pageable);
    for (Post post : postList) {
        PostPreview postDTO = mapper.postToPostPreview(post);
        posts.add(postDTO);
    }
    response.setCount(postRepository.getCountOfPostsByTag(tag));
    response.setPosts(posts);
    return response;
}


@Override
public ThePosts getPostsByDate(int offset,int limit,String date){
    ThePosts response = new ThePosts();
    List<PostPreview> posts = new ArrayList<>();
    PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
    Pageable pageable = PageRequest.of(offset / 10, limit);
    List<Post> postList = postRepository.getPostsByDate(dateBefore(date), dateAfter(date), pageable);
    for (Post post : postList) {
        PostPreview postDTO = mapper.postToPostPreview(post);
        posts.add(postDTO);
    }
    response.setCount(postRepository.getCountOfPostsByDate(dateBefore(date), dateAfter(date)));
    response.setPosts(posts);
    return response;
}


@Override
public ThePosts getMyPosts(int offset,int limit,String status){
    ThePosts response = new ThePosts();
    List<PostPreview> posts = new ArrayList<>();
    PostResponseMapper mapper = Mappers.getMapper(PostResponseMapper.class);
    Pageable pageable = PageRequest.of(offset / 10, limit);
    List<Post> postList;
    int count;
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    int id = authConfiguration.getAuths().get(currentSession);
    switch(status) {
        case "inactive":
            postList = postRepository.getMyInactivePosts(id, pageable);
            count = postRepository.getCountOfMyInactivePosts(id);
            break;
        case "pending":
            postList = postRepository.getMyPendingPosts(id, pageable);
            count = postRepository.getCountOfMyPendingPosts(id);
            break;
        case "declined":
            postList = postRepository.getMyDeclinedPosts(id, pageable);
            count = postRepository.getCountOfMyDeclinedPosts(id);
            break;
        default:
            postList = postRepository.getMyPublishedPosts(id, pageable);
            count = postRepository.getCountOfMyPublishedPosts(id);
    }
    for (Post post : postList) {
        PostPreview postResponse = mapper.postToPostPreview(post);
        posts.add(postResponse);
    }
    response.setCount(count);
    response.setPosts(posts);
    return response;
}


public int checkViewCount(Post post){
    String currentSession = RequestContextHolder.currentRequestAttributes().getSessionId();
    try {
        int id = authConfiguration.getAuths().get(currentSession);
        if (userRepository.isAdmin(id) == 1)
            return post.getViewCount();
        if (post.getUser().getId() == id)
            return post.getViewCount();
        return post.getViewCount() + 1;
    } catch (NullPointerException e) {
        return post.getViewCount() + 1;
    }
}


}