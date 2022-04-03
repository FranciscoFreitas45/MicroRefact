package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.PostJPARepository;
import kielce.tu.weaii.telelearn.models.courses.Post;
@Service
public class PostCourseService {

@Autowired
 private PostJPARepository postjparepository;


}