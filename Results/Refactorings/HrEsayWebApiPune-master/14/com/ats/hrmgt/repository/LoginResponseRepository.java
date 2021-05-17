import com.ats.hrmgt.model.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface LoginResponseRepository extends JpaRepository<LoginResponse, Integer> {


@Query(value = "SELECT\n" + "        e.emp_id,\n" + "        e.emp_code,\n" + "        e.first_name,\n" + "        e.middle_name,\n" + "        e.surname,\n" + "        e.mother_name,\n" + "        e.email_id,\n" + "        u.loc_id,\n" + "        u.user_id,\n" + "        u.user_pwd,\n" + "        e.ex_int1 as design_type,\n" + "        e.ex_var1 as hod_dept_ids,\n" + "        u.ex_int1 as is_visit,\n" + "        i.ex_var1 as emp_photo,0 as access_role_id \n" + "    from\n" + "        m_employees e,\n" + "        m_user u,\n" + "        tbl_emp_info i\n" + "    where\n" + "        e.emp_id=u.emp_id \n" + "        and BINARY u.user_name =:username \n" + "        and BINARY u.user_pwd =:password\n" + "        and e.del_status=1\n" + "        and i.emp_id=e.emp_id and u.ex_var1 IN (2,3) ", nativeQuery = true)
public LoginResponse loginProcess(String username,String password)


@Query(value = "SELECT\n" + "        e.emp_id,\n" + "        e.emp_code,\n" + "        e.first_name,\n" + "        e.middle_name,\n" + "        e.surname,\n" + "        e.mother_name,\n" + "        e.email_id,\n" + "        u.loc_id,\n" + "        u.user_id,\n" + "        u.user_pwd,\n" + "        e.ex_int1 as design_type,\n" + "        e.ex_var1 as hod_dept_ids,\n" + "        u.ex_int1 as is_visit,\n" + "        i.ex_var1 as emp_photo,e.emp_category as access_role_id\n" + "    from\n" + "        m_employees e,\n" + "        m_user u,\n" + "        tbl_emp_info i\n" + "    where\n" + "        e.emp_id=u.emp_id \n" + "        and BINARY u.user_name =:username \n" + "        and BINARY u.user_pwd =:password\n" + "        and e.del_status=1\n" + "        and i.emp_id=e.emp_id and u.ex_var1 IN (1,3) ", nativeQuery = true)
public LoginResponse loginProcessWebAppl(String username,String password)


@Query(value = "SELECT\n" + "        e.emp_id,\n" + "        e.emp_code,\n" + "        e.first_name,\n" + "        e.middle_name,\n" + "        e.surname,\n" + "        e.mother_name,\n" + "        i.email AS email_id," + "        u.loc_id,\n" + "        u.user_id,\n" + "        u.user_pwd,\n" + "        e.ex_int1 as design_type,\n" + "        e.ex_var1 as hod_dept_ids,\n" + "        u.ex_int1 as is_visit,\n" + "        i.ex_var1 as emp_photo, 0 as access_role_id \n" + "    from \n" + "        m_employees e,\n" + "        m_user u,\n" + "        tbl_emp_info i\n" + "    where\n" + "        e.emp_id=u.emp_id \n" + "        and BINARY u.user_name =:username \n" + "        and e.del_status=1\n" + "        and i.emp_id=e.emp_id", nativeQuery = true)
public LoginResponse CheckUserForPasswordByUsername(String username)


@Query(value = "SELECT\n" + "        e.emp_id,\n" + "        e.emp_code,\n" + "        e.first_name,\n" + "        e.middle_name,\n" + "        e.surname,\n" + "        e.mother_name,\n" + "        i.email AS email_id,\n" + "        u.loc_id,\n" + "        u.user_id,\n" + "        u.user_pwd,\n" + "        e.ex_int1 as design_type,\n" + "        e.ex_var1 as hod_dept_ids,\n" + "        u.ex_int1 as is_visit,\n" + "        i.ex_var1 as emp_photo, 0 as access_role_id \n" + "    from\n" + "        m_employees e,\n" + "        m_user u,\n" + "        tbl_emp_info i\n" + "    where\n" + "        e.emp_id=u.emp_id \n" + "        and BINARY i.email =:username \n" + "        and e.del_status=1\n" + "        and i.emp_id=e.emp_id", nativeQuery = true)
public LoginResponse CheckUserForPasswordByEmail(String username)


}