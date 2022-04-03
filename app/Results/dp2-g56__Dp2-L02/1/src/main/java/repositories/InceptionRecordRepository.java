package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.InceptionRecord;
@Repository
public interface InceptionRecordRepository extends JpaRepository<InceptionRecord, Integer>{


}