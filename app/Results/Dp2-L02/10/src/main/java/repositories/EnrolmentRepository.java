package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Enrolment;
@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Integer>{


}