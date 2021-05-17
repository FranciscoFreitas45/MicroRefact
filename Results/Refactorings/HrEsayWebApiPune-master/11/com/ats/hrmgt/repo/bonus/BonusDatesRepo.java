import com.ats.hrmgt.model.bonus.BonusDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface BonusDatesRepo extends JpaRepository<BonusDates, String> {


@Query(value = " SELECT\n" + "  UUID() as uid,\n" + "    TIMESTAMPDIFF(MONTH, fY_fromdt, fy_todt) +1 AS total_month,\n" + "    DATE_FORMAT(fY_fromdt, '%c') AS month_from,\n" + "    DATE_FORMAT(fY_fromdt, '%Y') AS year_from,\n" + "    DATE_FORMAT(fy_todt, '%c') AS month_to,\n" + "    DATE_FORMAT(fy_todt, '%Y') AS year_to\n" + "FROM\n" + "    m_bonus_fy\n" + "WHERE\n" + "    bonus_id =:bonusId ", nativeQuery = true)
public BonusDates getDates(int bonusId)


}