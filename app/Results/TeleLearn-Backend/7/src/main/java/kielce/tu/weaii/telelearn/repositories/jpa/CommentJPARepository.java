package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.courses.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentJPARepository extends JpaRepository<Comment, Long>{


}