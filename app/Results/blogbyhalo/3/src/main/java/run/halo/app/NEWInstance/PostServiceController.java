package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PostServiceController {

 private PostService postservice;


@GetMapping
("/pageBy")
public Page<Post> pageBy(@RequestParam(name = "keyword") String keyword,@RequestParam(name = "pageable") Pageable pageable){
  return postservice.pageBy(keyword,pageable);
}


@GetMapping
("/convertToListVo")
public List<PostListVO> convertToListVo(@RequestParam(name = "posts") List<Post> posts,@RequestParam(name = "queryEncryptCategory") boolean queryEncryptCategory){
  return postservice.convertToListVo(posts,queryEncryptCategory);
}


@GetMapping
("/convertToSimple")
public Object convertToSimple(@RequestParam(name = "Object") Object Object){
  return postservice.convertToSimple(Object);
}


@GetMapping
("/getById")
public Object getById(@RequestParam(name = "Object") Object Object){
  return postservice.getById(Object);
}


@GetMapping
("/convertToDetailVo")
public PostDetailVO convertToDetailVo(@RequestParam(name = "post") Post post,@RequestParam(name = "queryEncryptCategory") boolean queryEncryptCategory){
  return postservice.convertToDetailVo(post,queryEncryptCategory);
}


@PutMapping
("/publishVisitEvent")
public void publishVisitEvent(@RequestParam(name = "postId") Integer postId){
postservice.publishVisitEvent(postId);
}


@GetMapping
("/getBySlug")
public Object getBySlug(@RequestParam(name = "Object") Object Object){
  return postservice.getBySlug(Object);
}


@GetMapping
("/getPrevPost")
public Object getPrevPost(@RequestParam(name = "Object") Object Object){
  return postservice.getPrevPost(Object);
}


@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return postservice.orElseThrow(Object);
}


@GetMapping
("/getNextPost")
public Object getNextPost(@RequestParam(name = "Object") Object Object){
  return postservice.getNextPost(Object);
}


@GetMapping
("/increaseLike")
public Object increaseLike(@RequestParam(name = "Object") Object Object){
  return postservice.increaseLike(Object);
}


@GetMapping
("/convertToMinimal")
public Object convertToMinimal(@RequestParam(name = "Object") Object Object){
  return postservice.convertToMinimal(Object);
}


@GetMapping
("/countByStatus")
public Object countByStatus(@RequestParam(name = "Object") Object Object){
  return postservice.countByStatus(Object);
}


@GetMapping
("/createBy")
public PostDetailVO createBy(@RequestParam(name = "post") Post post,@RequestParam(name = "tagIds") Set<Integer> tagIds,@RequestParam(name = "categoryIds") Set<Integer> categoryIds,@RequestParam(name = "autoSave") boolean autoSave){
  return postservice.createBy(post,tagIds,categoryIds,autoSave);
}


@GetMapping
("/importMarkdown")
public PostDetailVO importMarkdown(@RequestParam(name = "markdown") String markdown,@RequestParam(name = "filename") String filename){
  return postservice.importMarkdown(markdown,filename);
}


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return postservice.listAll(Object);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return postservice.createInBatch(Object);
}


@GetMapping
("/listPostMarkdowns")
public List<PostMarkdownVO> listPostMarkdowns(){
  return postservice.listPostMarkdowns();
}


@GetMapping
("/countVisit")
public Object countVisit(@RequestParam(name = "Object") Object Object){
  return postservice.countVisit(Object);
}


@GetMapping
("/countLike")
public Object countLike(@RequestParam(name = "Object") Object Object){
  return postservice.countLike(Object);
}


}