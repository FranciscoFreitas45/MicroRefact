package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.MiscellaneousRecord;
@Repository
public interface MiscellaneousRecordRepository extends JpaRepository<MiscellaneousRecord, Integer>{


}