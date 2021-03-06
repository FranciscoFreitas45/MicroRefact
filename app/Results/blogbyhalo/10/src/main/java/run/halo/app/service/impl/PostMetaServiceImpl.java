package run.halo.app.service.impl;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.entity.PostMeta;
import run.halo.app.repository.PostRepository;
import run.halo.app.repository.base.BaseMetaRepository;
import run.halo.app.service.PostMetaService;
import run.halo.app.Interface.PostRepository;
@Slf4j
@Service
public class PostMetaServiceImpl extends BaseMetaServiceImpl<PostMeta>implements PostMetaService{

 private  PostRepository postRepository;

public PostMetaServiceImpl(BaseMetaRepository<PostMeta> baseMetaRepository, PostRepository postRepository) {
    super(baseMetaRepository);
    this.postRepository = postRepository;
}
@Override
public void validateTarget(Integer postId){
    postRepository.findById(postId).orElseThrow(() -> new NotFoundException("查询不到该文章的信息").setErrorData(postId));
}


}