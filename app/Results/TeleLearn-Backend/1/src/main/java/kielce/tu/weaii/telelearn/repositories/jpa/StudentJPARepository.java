package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentJPARepository extends JpaRepository<Student, Long>{


}