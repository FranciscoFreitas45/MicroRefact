import com.ats.hrmgt.model.advance.Advance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface AdvanceRepo extends JpaRepository<Advance, Integer> {


public List<Advance> findByDelStatusAndIsDedAndCmpId(int i,int j,int companyId)


public Advance findById(int i)


@Query(value = " select\n" + "        a.*\n" + "    from\n" + "        tbl_advance a,\n" + "        m_employees e\n" + "    where\n" + "        a.voucher_no=:voucherNo \n" + "        and a.del_status=:delStatus\n" + "        and e.location_id=:locationId\n" + "        and e.emp_id=a.emp_id", nativeQuery = true)
public List<Advance> findByVoucherNoAndDelStatus(String voucherNo,int delStatus,int locationId)


@Transactional
@Modifying
@Query("update Advance set  ded_month=:dedMonth,ded_year=:dedYear,skip_login_name =:userId, skip_login_time=:dateTimeUpdate,skip_id=:count ,skip_remarks=:skipStr WHERE id=:advId")
public int skipAdvance(int advId,int dedYear,int dedMonth,String dateTimeUpdate,int userId,int count,String skipStr)


@Transactional
@Modifying
@Query("delete from Advance where ded_month=:month and ded_year=:year and ex_int1=1 and del_status=1 and is_ded=0 and emp_id in (:empIds)")
public int deleteAdvanceBydefault(int month,int year,List<Integer> empIds)


@Transactional
@Modifying
@Query("update Advance set is_ded=1 where del_status=1 and is_ded=0 and emp_id=:empIds")
public int updateAdv(int empIds)


@Transactional
@Modifying
@Query("update Advance set del_status=0  WHERE id=:advId")
public int deleteAdvance(int advId)


public List<Advance> findByEmpIdAndDelStatus(int empId,int i)


}