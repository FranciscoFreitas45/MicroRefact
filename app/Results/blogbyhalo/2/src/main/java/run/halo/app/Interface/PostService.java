package run.halo.app.Interface;
public interface PostService {

   public PostDetailVO importMarkdown(String markdown,String filename);
   public Object listAll(Object Object);
   public Object createInBatch(Object Object);
   public List<PostMarkdownVO> listPostMarkdowns();
}