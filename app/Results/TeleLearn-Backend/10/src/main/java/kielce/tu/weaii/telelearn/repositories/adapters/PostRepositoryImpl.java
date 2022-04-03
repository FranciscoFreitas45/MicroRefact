package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.courses.Post;
import kielce.tu.weaii.telelearn.repositories.jpa.PostJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.PostRepository;
import org.springframework.stereotype.Repository;
@Repository
public class PostRepositoryImpl extends BaseCRUDRepositoryImpl<Post>implements PostRepository{

public PostRepositoryImpl(PostJPARepository postJPARepository) {
    super(postJPARepository);
}
}