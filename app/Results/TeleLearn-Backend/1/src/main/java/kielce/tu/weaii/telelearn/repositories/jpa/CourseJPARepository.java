package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseJPARepository extends JpaRepository<Course, Long>{


}