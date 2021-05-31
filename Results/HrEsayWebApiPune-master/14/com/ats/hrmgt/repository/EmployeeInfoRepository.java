import com.ats.hrmgt.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {


@Query(value = "SELECT\n" + "    e.*\n" + "FROM\n" + "    emp_info AS e\n" + "WHERE\n" + "    e.emp_id IN(\n" + "    SELECT\n" + "        emp_id\n" + "    FROM\n" + "        claim_authority\n" + "    WHERE\n" + "        ca_ini_auth_emp_id = :empId OR ca_fin_auth_emp_id = :empId AND del_status = 1\n" + ")", nativeQuery = true)
public List<EmployeeInfo> getEmpListForClaimByEmpId(int empId)


@Transactional
@Modifying
@Query("update EmployeeInfo set del_status=0  WHERE emp_id=:empId")
public int deleteEmpInfo(int empId)


public List<EmployeeInfo> findByEmpIdAndLocIdAndDelStatus(int empId,int calYrId,int i)


public List<EmployeeInfo> findByEmpMobile1AndDelStatusAndIsActive(String empMobile,int delStatus,int isActive)


@Query(value = "select * from emp_info where\n" + "        emp_id   in (\n" + "            select\n" + "                emp_id \n" + "            from\n" + "                project_allotment \n" + "            where\n" + "                 ((:fromDate BETWEEN pallot_fromdt and pallot_todt ) or  (:toDate BETWEEN pallot_fromdt and pallot_todt ) or (pallot_fromdt BETWEEN :fromDate and :toDate )\n" + "                 or (pallot_todt BETWEEN :fromDate and :toDate )) and ex_int1=1 ) and del_status=1 and is_active=1 and emp_cat_id=:catId and company_id=:companyId ", nativeQuery = true)
public List<EmployeeInfo> getPartialTimeFreeEmpList(String fromDate,String toDate,int catId,int companyId)


public EmployeeInfo findByDelStatusAndIsActiveAndEmpMobile1(int i,int j,String inputValue)


@Transactional
@Modifying
@Query("update EmployeeInfo set emp_photo=:imageName  WHERE emp_id=:empId")
public int updateEmpProfPic(int empId,String imageName)


@Query(value = "select * from emp_info where\n" + "        emp_id not in (\n" + "            select\n" + "                emp_id \n" + "            from\n" + "                project_allotment \n" + "            where\n" + "                 (:fromDate BETWEEN pallot_fromdt and pallot_todt ) or  (:toDate BETWEEN pallot_fromdt and pallot_todt ) or (pallot_fromdt BETWEEN :fromDate and :toDate )\n" + "                 or (pallot_todt BETWEEN :fromDate and :toDate )) and del_status=1 and is_active=1 and emp_cat_id=:catId and company_id=:companyId", nativeQuery = true)
public List<EmployeeInfo> getFullTimeFreeEmpList(String fromDate,String toDate,int catId,int companyId)


@Query(value = "select * from emp_info where emp_id not in (select emp_id from leave_balance_cal where cal_yr_id=(select cal_yr_id from dm_cal_year where is_current=1)) " + "and del_status=1 and loc_id in (:locationId)", nativeQuery = true)
public List<EmployeeInfo> getemplistwhichisnotyearend(List<Integer> locationId)


public List<EmployeeInfo> findByDelStatus(int i)


@Query(value = "SELECT\n" + "    e.*\n" + "FROM\n" + "    emp_info AS e\n" + "WHERE\n" + "    e.emp_id IN(\n" + "    SELECT\n" + "        emp_id\n" + "    FROM\n" + "        leave_authority\n" + "    WHERE\n" + "        ini_auth_emp_id =:empId OR fin_auth_emp_id = :empId AND del_status = 1\n" + ")", nativeQuery = true)
public List<EmployeeInfo> getEmployeeListByEmpId(int empId)


public EmployeeInfo findByDelStatusAndIsActiveAndEmpEmail(int i,int j,String inputValue)


public List<EmployeeInfo> findByEmpEmailAndDelStatusAndIsActive(String empEmail,int delStatus,int isActive)


@Query(value = "SELECT\n" + "    e.*\n" + "FROM\n" + "    emp_info AS e\n" + "WHERE\n" + "    e.loc_id IN(:locationId) and e.del_status=:i", nativeQuery = true)
public List<EmployeeInfo> getEmpInfoByLocId(List<Integer> locationId,int i)


@Transactional
@Modifying
@Query("update EmployeeInfo set emp_joining_date=:joinDate  WHERE emp_id=:empId")
public int updateEmpJoinigDate(int empId,String joinDate)


public EmployeeInfo findByEmpIdAndDelStatus(int empId,int i)


@Transactional
@Modifying
@Query("update EmployeeInfo set ex_var1=:token  WHERE emp_id=:empId")
public int updateUserToken(int empId,String token)


}