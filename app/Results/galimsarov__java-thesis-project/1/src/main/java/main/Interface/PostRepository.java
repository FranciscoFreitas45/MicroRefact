package main.Interface;
public interface PostRepository {

   public Object getOne(Object Object);
   public Object saveAndFlush(Object Object);
   public Object findAll(Object Object);
   public List<String> getPostsForTheYear(String year);
   public int getPostsCountOfUser(int userId);
   public int getLikesCountOfUsersPosts(int userId);
   public int getDisLikesCountOfUsersPosts(int userId);
   public int getViewsCountOfUsersPosts(int userId);
   public Date getFirstPostOfUser(int userId);
   public Object getTime(Object Object);
   public int getPostsCount();
   public int getLikesCount();
   public int getDisLikesCount();
   public int getViewsCount();
   public Date getFirstPost();
}