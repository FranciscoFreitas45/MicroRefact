package sn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sn.repositories.PostRepository;
import sn.model.Post;
@Service
public class PostPersonService {

@Autowired
 private PostRepository postrepository;


}