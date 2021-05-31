import com.ats.hrmgt.model.dashboard.GetNewHiresDash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface GetNewHiresDashRepo extends JpaRepository<GetNewHiresDash, String> {


@Query(value = "SELECT\n" + " UUID() as uni_key ,  (\n" + "    SELECT\n" + "        COUNT(tei.salary_info_id)\n" + "    FROM\n" + "        tbl_emp_salary_info tei,\n" + "        tbl_emp_info einfo,m_employees e\n" + "    WHERE\n" + "        e.emp_id=einfo.emp_id and e.location_id=:locId and einfo.gender = 'MALE' AND tei.emp_id = einfo.emp_id AND tei.cmp_joining_date BETWEEN DATE_ADD(:currDate, INTERVAL -30 DAY) AND :currDate AND tei.del_status = 1\n" + ") AS male_emp,\n" + "(\n" + "    SELECT\n" + "        COUNT(teiNew.salary_info_id)\n" + "    FROM\n" + "        tbl_emp_salary_info teiNew,\n" + "        tbl_emp_info einfoNew,m_employees e\n" + "    WHERE\n" + "        e.emp_id=einfoNew.emp_id and e.location_id=:locId and einfoNew.gender = 'FEMALE' AND teiNew.emp_id = einfoNew.emp_id AND teiNew.cmp_joining_date BETWEEN DATE_ADD(:currDate, INTERVAL -30 DAY) AND :currDate AND teiNew.del_status = 1\n" + ") AS female_emp,\n" + "(\n" + "    SELECT\n" + "        COUNT(teiNew.salary_info_id)\n" + "    FROM\n" + "        tbl_emp_salary_info teiNew,\n" + "        tbl_emp_info einfoNew,m_employees e\n" + "    WHERE\n" + "        e.emp_id=einfoNew.emp_id and e.location_id=:locId and einfoNew.gender = ! 'FEMALE' AND einfoNew.gender = ! 'MALE' AND teiNew.emp_id = einfoNew.emp_id AND teiNew.cmp_joining_date BETWEEN DATE_ADD(:currDate, INTERVAL -30 DAY) AND :currDate AND teiNew.del_status = 1\n" + ") AS oth_emp", nativeQuery = true)
public GetNewHiresDash getTodaysHire(String currDate,int locId)


@Query(value = "SELECT UUID() as uni_key , ( SELECT COUNT(DISTINCT tbl_emp_info.emp_id) FROM tbl_emp_info,m_employees e WHERE tbl_emp_info.gender = 'MALE' AND " + "tbl_emp_info.del_status = 1 and e.emp_id=tbl_emp_info.emp_id and e.location_id = :locId) AS male_emp, ( SELECT COUNT(DISTINCT tbl_emp_info.emp_id) " + "FROM tbl_emp_info,m_employees e WHERE tbl_emp_info.gender = 'FEMALE' AND tbl_emp_info.del_status = 1 and e.emp_id=tbl_emp_info.emp_id and e.location_id = :locId) " + "AS female_emp, ( 0) AS oth_emp", nativeQuery = true)
public GetNewHiresDash getAgeDiversity(int locId)


}