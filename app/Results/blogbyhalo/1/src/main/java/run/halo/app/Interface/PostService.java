package run.halo.app.Interface;
public interface PostService {

   public Object countByStatus(Object Object);
   public PostDetailVO createBy(Post post,Set<Integer> tagIds,Set<Integer> categoryIds,boolean autoSave);
}