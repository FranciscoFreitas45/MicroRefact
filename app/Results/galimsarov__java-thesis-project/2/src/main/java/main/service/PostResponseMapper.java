package main.service;
 import main.model;
import main.model.response.ids;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Mapper
public class PostResponseMapper {


public PostPreview postToPostPreview(Post post){
    PostPreview response = new PostPreview();
    response.setId(post.getId());
    response.setTimestamp(post.getTime().getTime() / 1000);
    IdNameResp user = new IdNameResp();
    user.setId(post.getUser().getId());
    user.setName(post.getUser().getName());
    response.setUser(user);
    response.setTitle(post.getTitle());
    response.setAnnounce(getAnnounceDTO(post));
    response.setLikeCount(getLikes(post));
    response.setDislikeCount(getDislikes(post));
    response.setCommentCount(post.getPostCommentList().size());
    response.setViewCount(post.getViewCount());
    return response;
}


public List<CommentResponse> getCommentsResponse(List<PostComment> comments){
    List<CommentResponse> response = new ArrayList<>();
    for (PostComment comment : comments) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setTimestamp(comment.getTime().getTime() / 1000);
        commentResponse.setText(comment.getText());
        IdNamePhotoResp userWithPhoto = new IdNamePhotoResp();
        userWithPhoto.setId(comment.getUser().getId());
        userWithPhoto.setName(comment.getUser().getName());
        userWithPhoto.setPhoto(comment.getUser().getPhoto());
        commentResponse.setUser(userWithPhoto);
        if (comment.getParentId() != null)
            commentResponse.setParentId(comment.getParentId());
        response.add(commentResponse);
    }
    return response;
}


public String getAnnounceDTO(Post post){
    Pattern pattern = Pattern.compile("</?[a-z]+>");
    Matcher matcher = pattern.matcher(post.getText());
    String answer = matcher.replaceAll("...");
    pattern = Pattern.compile("&nbsp;");
    matcher = pattern.matcher(answer);
    answer = matcher.replaceAll("...");
    pattern = Pattern.compile("<[a-z]+ .+\">");
    matcher = pattern.matcher(answer);
    answer = matcher.replaceAll("...");
    int sizeOfAnnounce = 200;
    if (answer.length() < sizeOfAnnounce)
        return answer;
    else {
        answer = answer.substring(0, sizeOfAnnounce);
        return answer.trim() + "...";
    }
}


public int getLikes(Post post){
    int likesCount = 0;
    for (PostVote postVote : post.getPostVoteList()) if (postVote.getValue() == 1)
        likesCount++;
    return likesCount;
}


public int getDislikes(Post post){
    int disLikesCount = 0;
    for (PostVote postVote : post.getPostVoteList()) if (postVote.getValue() == -1)
        disLikesCount++;
    return disLikesCount;
}


public PostResponse postToPostResponse(Post post){
    PostResponse response = new PostResponse();
    response.setId(post.getId());
    response.setTimestamp(post.getTime().getTime() / 1000);
    response.setActive(post.isActive());
    IdNameResp userDTO = new IdNameResp();
    userDTO.setId(post.getUser().getId());
    userDTO.setName(post.getUser().getName());
    response.setUser(userDTO);
    response.setTitle(post.getTitle());
    response.setText(post.getText());
    response.setLikeCount(getLikes(post));
    response.setDislikeCount(getDislikes(post));
    response.setViewCount(post.getViewCount());
    response.setComments(getCommentsResponse(post.getPostCommentList()));
    Set<Tag> tagSet = post.getTagSet();
    if (tagSet.size() != 0) {
        Set<String> stringSet = new HashSet<>();
        for (Tag tag : tagSet) stringSet.add(tag.getName());
        response.setTags(stringSet);
    } else
        response.setTags(new HashSet<>());
    return response;
}


}