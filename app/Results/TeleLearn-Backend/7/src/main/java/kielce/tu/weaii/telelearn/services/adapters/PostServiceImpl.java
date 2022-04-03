package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.courses.CommentNotFound;
import kielce.tu.weaii.telelearn.exceptions.courses.MovePostNotPossible;
import kielce.tu.weaii.telelearn.exceptions.courses.PostCommentingNotAllowed;
import kielce.tu.weaii.telelearn.exceptions.courses.PostNotFoundException;
import kielce.tu.weaii.telelearn.models.Attachment;
import kielce.tu.weaii.telelearn.models.AttachmentData;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Comment;
import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.models.courses.Post;
import kielce.tu.weaii.telelearn.models.courses.PostVisibility;
import kielce.tu.weaii.telelearn.repositories.jpa.CommentJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.PostRepository;
import kielce.tu.weaii.telelearn.requests.courses.PostCommentRequest;
import kielce.tu.weaii.telelearn.requests.courses.PostRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports.CourseService;
import kielce.tu.weaii.telelearn.services.ports.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

 private  PostRepository postRepository;

 private  CourseService courseService;

 private  UserServiceDetailsImpl userServiceDetails;

 private  CommentJPARepository commentJpaRepository;


@Override
@Transactional
public void deleteComment(Long id){
    User currentUser = userServiceDetails.getCurrentUser();
    Comment comment = commentJpaRepository.findById(id).orElseThrow(() -> new CommentNotFound(id));
    if (userNotPermittedToDeleteComment(currentUser, comment)) {
        throw new AuthorizationException("usuwanie komentarza", currentUser.getId(), id);
    }
    commentJpaRepository.delete(comment);
}


public boolean isUserNotPermittedToDelete(Post post,User currentUser){
    return !currentUser.getUserRole().equals(UserRole.ADMIN) && !currentUser.getUserRole().equals(UserRole.TEACHER) && !post.getAuthor().getId().equals(currentUser.getId());
}


public Post preparePost(PostRequest request,List<MultipartFile> attachments,Course course){
    LocalDateTime now = LocalDateTime.now();
    Post post = new Post();
    post.setAuthor(userServiceDetails.getCurrentUser());
    post.setContent(request.getContent());
    post.setPostVisibility(request.getPostVisibility());
    post.setPublicationTime(now);
    post.setCourse(course);
    post.setCommentingAllowed(request.isCommentingAllowed());
    if (attachments != null && !attachments.isEmpty()) {
        post.setAttachments(prepareAttachments(attachments, now, post));
    }
    return post;
}


@Override
@Transactional
public Post addPost(PostRequest request,List<MultipartFile> attachments){
    Course course = courseService.getById(request.getCourseId());
    User currentUser = userServiceDetails.getCurrentUser();
    if (currentUser.getUserRole().equals(UserRole.STUDENT) && !course.isStudentsAllowedToPost()) {
        throw new AuthorizationException("dodawanie postu", currentUser.getId(), course.getId());
    }
    return postRepository.save(preparePost(request, attachments, course));
}


public boolean userNotPermittedToDeleteComment(User currentUser,Comment comment){
    return currentUser.getUserRole().equals(UserRole.TEACHER) && !comment.getPost().getCourse().getOwner().getId().equals(currentUser.getId()) || !comment.getAuthor().getId().equals(currentUser.getId());
}


public void checkPostAuthorization(Post post){
    User currentUser = userServiceDetails.getCurrentUser();
    if (currentUser.getUserRole().equals(UserRole.ADMIN)) {
        return;
    }
    if (isCurrentUserNotCurseOwner(post, currentUser)) {
        throw new AuthorizationException("wyświetlanie postów kursu", currentUser.getId(), post.getCourse().getId());
    }
    if (isCurrentStudentInNotAllowedToSeePost(post, currentUser)) {
        throw new AuthorizationException("wyświetlanie postów kursu", currentUser.getId(), post.getCourse().getId());
    }
}


@Override
public List<Post> getCoursePosts(Long courseId){
    Course course = courseService.getById(courseId);
    User currentUser = userServiceDetails.getCurrentUser();
    UserRole currentUserRole = currentUser.getUserRole();
    if (currentUserRole.equals(UserRole.ADMIN) || currentUserRole.equals(UserRole.TEACHER)) {
        List<Post> posts = course.getPosts();
        posts.sort(Comparator.comparing(Post::getPublicationTime).reversed());
        return posts;
    } else {
        return course.getPosts().stream().filter(post -> post.getPostVisibility().equals(PostVisibility.EVERYONE) || post.getAuthor().getId().equals(currentUser.getId())).sorted(Comparator.comparing(Post::getPublicationTime).reversed()).collect(Collectors.toList());
    }
}


public List<Attachment> prepareAttachments(List<MultipartFile> attachments,LocalDateTime now,Post post){
    List<Attachment> attachmentList = new ArrayList<>();
    for (MultipartFile file : attachments) {
        Attachment attachment = new Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setUploadTime(now);
        AttachmentData attachmentData = new AttachmentData();
        attachmentData.setData(file.getBytes());
        attachmentData.setAttachment(attachment);
        attachment.setAttachmentData(Arrays.asList(attachmentData));
        attachmentList.add(attachment);
        attachment.setPost(post);
    }
    return attachmentList;
}


public void updatePost(PostRequest postRequest,List<MultipartFile> newAttachments,Post post){
    post.setContent(postRequest.getContent());
    post.setPostVisibility(postRequest.getPostVisibility());
    post.setCommentingAllowed(postRequest.isCommentingAllowed());
    if (postRequest.getAttachmentIdsToDelete() != null) {
        for (long attachmentId : postRequest.getAttachmentIdsToDelete()) {
            post.getAttachments().removeIf(attachment -> {
                if (attachment.getId() == null) {
                    return false;
                }
                return attachment.getId().equals(attachmentId);
            });
        }
    }
    post.getAttachments().addAll(prepareAttachments(newAttachments, LocalDateTime.now(), post));
}


@Override
public Post getById(Long id){
    Post post = postRepository.getById(id).orElseThrow(() -> new PostNotFoundException(id));
    checkPostAuthorization(post);
    return post;
}


public boolean isCurrentUserNotCurseOwner(Post post,User currentUser){
    return currentUser.getUserRole().equals(UserRole.TEACHER) && !post.getCourse().getOwner().getId().equals(currentUser.getId());
}


@Override
public List<Comment> getComments(Long postId){
    List<Comment> comments = getById(postId).getComments();
    comments.sort(Comparator.comparing(Comment::getPublicationTime).reversed());
    return comments;
}


public void verifyUpdateRequest(PostRequest postRequest,Post post){
    if (!postRequest.getCourseId().equals(post.getCourse().getId())) {
        throw new MovePostNotPossible();
    }
    User currentUser = userServiceDetails.getCurrentUser();
    if (!post.getAuthor().getId().equals(currentUser.getId())) {
        throw new AuthorizationException("edycja posta", currentUser.getId(), post.getId());
    }
}


@Override
@Transactional
public List<Comment> addComment(Long postId,PostCommentRequest postCommentRequest){
    Post post = getById(postId);
    if (!post.isCommentingAllowed()) {
        throw new PostCommentingNotAllowed();
    }
    Comment comment = new Comment();
    comment.setAuthor(userServiceDetails.getCurrentUser());
    comment.setContent(postCommentRequest.getContent());
    comment.setPost(post);
    comment.setPublicationTime(LocalDateTime.now());
    post.getComments().add(comment);
    return postRepository.save(post).getComments();
}


@Override
@Transactional
public void removePost(Long id){
    Post post = getById(id);
    User currentUser = userServiceDetails.getCurrentUser();
    if (isUserNotPermittedToDelete(post, currentUser)) {
        throw new AuthorizationException("usuwanie posta", currentUser.getId(), post.getId());
    }
    postRepository.delete(post);
}


public boolean isCurrentStudentInNotAllowedToSeePost(Post post,User currentUser){
    return currentUser.getUserRole().equals(UserRole.STUDENT) && ((!post.getAuthor().getId().equals(currentUser.getId()) && post.getPostVisibility().equals(PostVisibility.ONLY_TEACHER)) || post.getCourse().getStudents().stream().noneMatch(entry -> entry.getStudent().getId().equals(currentUser.getId())));
}


}