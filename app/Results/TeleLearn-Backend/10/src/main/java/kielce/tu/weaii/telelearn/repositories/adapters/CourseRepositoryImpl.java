package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.courses.Course;
import kielce.tu.weaii.telelearn.repositories.jpa.CourseJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.CourseRepository;
import org.springframework.stereotype.Repository;
@Repository
public class CourseRepositoryImpl extends BaseCRUDRepositoryImpl<Course>implements CourseRepository{

public CourseRepositoryImpl(CourseJPARepository jpaRepository) {
    super(jpaRepository);
}
}