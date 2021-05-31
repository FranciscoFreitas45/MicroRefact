import com.ats.hrmgt.model.advance.GetAdvance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetAdvanceRepo extends JpaRepository<GetAdvance, Integer> {


@Query(value = "SELECT\n" + "        tbl_advance.*,\n" + "        m_employees.emp_code,\n" + "        m_employees.first_name,\n" + "        m_employees.middle_name,\n" + "        m_employees.surname,\n" + "        m_designation.name as designation \n" + "    FROM\n" + "        m_employees,\n" + "        tbl_advance,\n" + "        m_designation \n" + "    WHERE\n" + "        tbl_advance.del_status = 1  \n" + "        AND  tbl_advance.emp_id = m_employees.emp_id \n" + "        AND m_designation.desig_id = m_employees.designation_id  \n" + "        AND tbl_advance.emp_id=:empId\n" + "    order by adv_date desc", nativeQuery = true)
public List<GetAdvance> getAdvanceHistoryByEmpId(int empId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "    m_designation.name as designation\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId AND YEAR(tbl_advance.adv_date)=:calYrId AND tbl_advance.emp_id=:empId", nativeQuery = true)
public List<GetAdvance> getSpecAdv(int companyId,int empId,String calYrId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "    m_designation.name as designation\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId ", nativeQuery = true)
public List<GetAdvance> getAllAdv(int companyId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "    m_designation.name as designation\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1  AND tbl_advance.is_ded=0 AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId and m_employees.location_id in (:locId)", nativeQuery = true)
public List<GetAdvance> getPendingAdvanceLocId(int companyId,List<Integer> locId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "     (\n" + "    SELECT\n" + "        CONCAT(\n" + "            m_employees.first_name,\n" + "            ' ',\n" + "            m_employees.surname\n" + "        )\n" + "    FROM\n" + "        m_employees\n" + "    WHERE\n" + "        m_employees.emp_id = tbl_advance.skip_login_name\n" + ") AS designation \n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    m_employees.location_id=:locId and tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId AND MONTH(tbl_advance.adv_date)=:month AND YEAR(tbl_advance.adv_date)=:year ORDER BY tbl_advance.emp_id ASC", nativeQuery = true)
public List<GetAdvance> getSpecEmpAdvForReport(int companyId,int month,int year,int locId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "    m_designation.name as designation\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId  AND YEAR(tbl_advance.adv_date)=:calYrId", nativeQuery = true)
public List<GetAdvance> getSpecYearAdv(int companyId,String calYrId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "    m_designation.name as designation\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId AND tbl_advance.emp_id=:empId", nativeQuery = true)
public List<GetAdvance> getSpecEmpAdv(int companyId,int empId)


@Query(value = " SELECT\n" + "    tbl_advance.*,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.middle_name,\n" + "    m_employees.surname,\n" + "    m_designation.name as designation\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1  AND tbl_advance.is_ded=0 AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId ", nativeQuery = true)
public List<GetAdvance> getSubModule(int companyId)


}