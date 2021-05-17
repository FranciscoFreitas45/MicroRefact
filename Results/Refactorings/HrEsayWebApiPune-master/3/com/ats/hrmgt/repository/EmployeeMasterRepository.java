import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {


@Query(value = "SELECT count(emp_id) FROM m_employees WHERE cmp_code=:companyId AND designation_id=:desigId and del_status=1", nativeQuery = true)
public int getEmpInfoByDesigId(int desigId,int companyId)


public List<EmployeeMaster> findByDelStatusAndCmpCodeOrderByEmpIdDesc(int del,int companyId)


public List<EmployeeMaster> findByLocationIdAndDelStatus(int locId,int i)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET current_shiftid =:shiftId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignShift(List<Integer> empIdList,String shiftId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET emp_type =:typeId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignEmpType(List<Integer> empIdList,String typeId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET depart_id =:deptId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignDept(List<Integer> empIdList,String deptId)


public List<EmployeeMaster> findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(int del,int companyId,int subCompId)


@Query(value = "select\n" + "        * \n" + "    from\n" + "        m_employees \n" + "    where\n" + "        emp_id not in (\n" + "            select\n" + "                emp_id \n" + "            from\n" + "                leave_balance_cal \n" + "            where\n" + "                cal_yr_id=(\n" + "                    select\n" + "                        cal_yr_id \n" + "                    from\n" + "                        dm_cal_year \n" + "                    where\n" + "                        is_current=1\n" + "                )\n" + "            ) and  del_status=1", nativeQuery = true)
public List<EmployeeMaster> getemplistwhichisnotyearend()


public List<EmployeeMaster> findByEmpTypeAndDelStatus(int empTypeId,int i)


@Query(value = "SELECT count(emp_id) FROM m_employees WHERE cmp_code=:companyId AND contractor_id=:contractId and del_status=1", nativeQuery = true)
public int getEmpInfoByContractId(int contractId,int companyId)


@Query(value = "SELECT e.* from m_employees e where e.del_status=1  ORDER BY e.emp_id DESC LIMIT 1", nativeQuery = true)
public EmployeeMaster getEmpMax()


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  emp_code =:empCode WHERE emp_id IN(:id)", nativeQuery = true)
public int submitupdateempcode(String empCode,int id)


@Query(value = "SELECT e.* from m_employees e  where e.del_status=1 AND e.current_shiftid=0", nativeQuery = true)
public List<EmployeeMaster> getEmpSalAssign()


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET location_id =:locId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignLocation(List<Integer> empIdList,String locId)


@Query(value = "select\n" + "        * \n" + "    from\n" + "        m_employees \n" + "    where\n" + "        emp_id not in (\n" + "            select\n" + "                emp_id \n" + "            from\n" + "                leave_balance_cal \n" + "            where\n" + "                cal_yr_id=(\n" + "                    select\n" + "                        cal_yr_id \n" + "                    from\n" + "                        dm_cal_year \n" + "                    where\n" + "                        is_current=1\n" + "                )\n" + "            ) and  del_status=1 and location_id=:locId", nativeQuery = true)
public List<EmployeeMaster> getemplistwhichisnotyearendByEmpId(int locId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  emp_category =:upDateId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int empEmpCategoryUpdate(List<Integer> empIdList,String upDateId)


public List<EmployeeMaster> findByDelStatusAndCmpCodeAndLocationIdOrderByEmpIdDesc(int del,int companyId,int locationId)


@Query(value = "SELECT count(emp_id) FROM m_employees WHERE cmp_code=:companyId AND depart_id=:deptId and del_status=1", nativeQuery = true)
public int getEmpInfoByDepartment(int deptId,int companyId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  ex_int2 =:skillId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int empSkillUpdate(List<Integer> empIdList,String skillId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  holiday_category   =:holiCatId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignHoliCat(List<Integer> empIdList,String holiCatId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET designation_id =:desnId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignDesignation(List<Integer> empIdList,String desnId)


public EmployeeMaster findByEmpCodeAndDelStatus(String empcode,int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  weekend_category =:holiCatId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int weekHoliCat(List<Integer> empIdList,String holiCatId)


public EmployeeMaster findByEmpCode(String empcode)


public List<EmployeeMaster> findByDelStatusAndEmpId(int del,int empId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  notice_pay_amount =:isActive WHERE emp_id IN(:empId)", nativeQuery = true)
public int deleteEmployeeStatus(int empId,int isActive)


@Query(value = "SELECT e.* from m_employees e  where e.emp_id=:empId", nativeQuery = true)
public EmployeeMaster getEmpInfoByEmpId(int empId)


@Query(value = " SELECT * FROM m_employees WHERE del_status=1 and location_id in (:locId)", nativeQuery = true)
public List<EmployeeMaster> getEmplistForAssignAuthorityAll(List<Location> locId)


public List<EmployeeMaster> findByCmpCodeAndDelStatusOrderByEmpIdDesc(int companyId,int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET del_status=0 WHERE emp_id=:empId", nativeQuery = true)
public int deleteEmployee(int empId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET sub_cmp_id =:compId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int assignComapny(List<Integer> empIdList,String compId)


@Transactional
@Modifying
@Query(value = "UPDATE m_employees SET  next_shiftid =:upDateId WHERE emp_id IN(:empIdList)", nativeQuery = true)
public int empEmpShiftGroupUpdate(List<Integer> empIdList,String upDateId)


public EmployeeMaster findByEmpIdAndDelStatus(int empId,int del)


}