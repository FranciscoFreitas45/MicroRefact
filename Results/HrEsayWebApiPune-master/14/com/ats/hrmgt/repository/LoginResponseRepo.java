import com.ats.hrmgt.model.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface LoginResponseRepo extends JpaRepository<LoginResponse, Integer> {


@Query(value = "select u.user_id,u.loc_id as location_ids, u.user_name, u.user_pwd,e.*,c.company_name,ec.emp_cat_name,et.emp_type_name,et.emp_type_access," + "dp.emp_dept_name,l.loc_name,c.company_logo,u.ex_int1 as is_visit from m_user u,emp_info e,m_company c,m_emp_category ec,m_emp_type et,m_emp_department dp,m_location l where" + " e.emp_id=u.emp_id and BINARY u.user_name =:userName and BINARY u.user_pwd =:pass and c.company_id=e.company_id and " + "ec.emp_cat_id = e.emp_cat_id and et.emp_type_id = e.emp_type_id and dp.emp_dept_id = e.emp_dept_id and l.loc_id = e.loc_id and e.del_status=1 " + " and e.is_active=1", nativeQuery = true)
public LoginResponse loginUser(String userName,String pass)


}