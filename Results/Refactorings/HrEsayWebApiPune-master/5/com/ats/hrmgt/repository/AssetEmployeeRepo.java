import com.ats.hrmgt.model.AssetEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssetEmployeeRepo extends JpaRepository<AssetEmployee, Integer> {


@Query(value = "SELECT\n" + "    emp.emp_id,\n" + "    emp.emp_code,\n" + "    emp.first_name,\n" + "    emp.middle_name,\n" + "    emp.surname,\n" + "    emp.emp_type,\n" + "    desig.name AS designation,\n" + "    depart.name AS department,\n" + "    loc.loc_name AS location," + "    loc.loc_id," + " 	 emp.ex_int1,\n" + "    emp.ex_var1\n" + "FROM\n" + "    m_employees emp, \n" + "    m_user usr,\n" + "    m_designation desig,\n" + "    m_department depart,\n" + "    m_location loc\n" + "WHERE\n" + "    emp.emp_id=desig.desig_id AND\n" + "    emp.depart_id=depart.depart_id AND\n" + "    emp.emp_id=usr.user_id AND\n" + "    find_in_set(loc.loc_id, usr.loc_id)AND\n" + "    loc.loc_id=:locId", nativeQuery = true)
public List<AssetEmployee> getAllAssetsEmpByLocation(int locId)


@Query(value = "SELECT\n" + "    emp.emp_id,\n" + "    emp.emp_code,\n" + "    emp.first_name,\n" + "    emp.middle_name,\n" + "    emp.surname,\n" + "    emp.emp_type,\n" + "    desig.name AS designation,\n" + "    depart.name AS department,\n" + "    loc.loc_name AS location,\n" + "    loc.loc_id," + " 	 emp.ex_int1,\n" + "    emp.ex_var1\n" + "FROM\n" + "    m_employees emp, \n" + "    m_user usr,\n" + "    m_designation desig,\n" + "    m_department depart,\n" + "    m_location loc\n" + "WHERE\n" + "    emp.emp_id=desig.desig_id AND\n" + "    emp.depart_id=depart.depart_id AND\n" + "    emp.emp_id=usr.user_id AND\n" + "    find_in_set(loc.loc_id, usr.loc_id)AND\n" + "    loc.loc_id=:locId AND\n" + "    emp.emp_id=:empId", nativeQuery = true)
public AssetEmployee getAssetsEmpByLocationAndEmpId(int locId,int empId)


}