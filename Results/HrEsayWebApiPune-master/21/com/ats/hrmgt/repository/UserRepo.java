import com.ats.hrmgt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface UserRepo extends JpaRepository<User, Integer> {


public User findByEmpId(int empId)


@Transactional
@Modifying
@Query("update User set user_pwd=:password,ex_int1=0  WHERE emp_id=:empId")
public int updateIsVistStatus(int empId,String password)


@Transactional
@Modifying
@Query("update User set user_pwd=:password  WHERE emp_id=:empId")
public int updateUserPassword(int empId,String password)


@Query(value = "select e.email_id from m_employees e,m_user u where u.user_name=:inputValue and u.emp_id=e.emp_id", nativeQuery = true)
public String getUserByEmailId(String inputValue)


@Query(value = " SELECT * FROM m_user WHERE BINARY m_user.user_pwd=:password AND  m_user.emp_id=:empId and m_user.del_status=1", nativeQuery = true)
public User getSpecificUserRecord(int empId,String password)


@Transactional
@Modifying
@Query("update User set user_pwd=:password,ex_int1=:isVisit  WHERE emp_id=:empId")
public int updateUserPassAndExInt1(int empId,String password,int isVisit)


@Transactional
@Modifying
@Query("update User set ex_var1=:loginType  WHERE emp_id IN (:empIdList)")
public int updateUserLoginType(List<Integer> empIdList,String loginType)


@Transactional
@Modifying
@Query("update User set loc_id=:loc  WHERE emp_id IN (:empIdList)")
public int updateAccLoc(List<Integer> empIdList,String loc)


}