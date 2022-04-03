package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.LegalRecord;
@Repository
public interface LegalRecordRepository extends JpaRepository<LegalRecord, Integer>{


}