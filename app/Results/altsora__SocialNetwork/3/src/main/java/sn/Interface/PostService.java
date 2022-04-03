package sn.Interface;
public interface PostService {

   public List<Post> findAllByPersonId(long personId,int offset,int itemPerPage);
   public WallPostResponse getExistsWallPost(Post post,PersonResponse author,List<CommentResponse> comments);
   public int getTotalCountPostsByPersonId(long personId);
   public Post addPost(Person author,String title,String text,LocalDateTime postTime);
   public WallPostResponse createNewWallPost(Post post,PersonResponse author);
}