package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.PeriodRecord;
@Repository
public interface PeriodRecordRepository extends JpaRepository<PeriodRecord, Integer>{


}