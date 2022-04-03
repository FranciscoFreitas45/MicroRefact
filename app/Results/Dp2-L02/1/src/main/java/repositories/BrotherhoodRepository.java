package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Brotherhood;
import domain.Float;
import domain.Parade;
@Repository
public interface BrotherhoodRepository extends JpaRepository<Brotherhood, Integer>{


@Query("select b.floats from Brotherhood b where b.id = ?1")
public List<Float> getFloatsByBrotherhood(int id)
;

@Query("select a from Brotherhood a join a.userAccount b where b.username = ?1")
public Brotherhood getBrotherhoodByUsername(String a)
;

@Query("select b.parades from Brotherhood b where b.id = ?1")
public List<Parade> getParadesByBrotherhood(int id)
;

@Query("select b.id from Brotherhood b where b.history.inceptionRecord.id=?1")
public int getBrotherhoodIdByInceptionRecord(int inceptionRecordId)
;

@Query("select c.id from Brotherhood c where c.history = (select a from History a join a.legalRecords b where b.id =?1)")
public int getBrotherhoodIdByLegalRecord(int legalRecordId)
;

@Query("select p from Brotherhood b join b.parades p where p.paradeStatus='ACCEPTED' and b.id = ?1")
public List<Parade> getParadesByBrotherhoodFinal(int id)
;

@Query("select a from Brotherhood a where a.area.id = ?1")
public List<Brotherhood> getBrotherhoodByArea(Integer a)
;

@Query("select c.id from Brotherhood c where c.history = (select a from History a join a.periodRecords b where b.id =?1)")
public int getBrotherhoodIdByPeriodRecord(int periodRecordId)
;

}