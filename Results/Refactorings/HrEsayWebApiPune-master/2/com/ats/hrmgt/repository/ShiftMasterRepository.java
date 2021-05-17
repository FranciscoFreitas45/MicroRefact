import com.ats.hrmgt.model.ShiftMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface ShiftMasterRepository extends JpaRepository<ShiftMaster, Integer> {


@Query(value = " select\n" + "        id,\n" + "        CONCAT(shiftname,\n" + "        ' (',\n" + "        m_self_grup.name,\n" + "        ')') as shiftname,\n" + "        fromtime,\n" + "        totime,\n" + "        changeable,\n" + "        changewith,\n" + "        company_id,\n" + "        max_late_time_allowed,\n" + "        CONCAT(FLOOR(shift_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(shift_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_hr,\n" + "        CONCAT(FLOOR(shift_halfday_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(shift_halfday_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_halfday_hr,\n" + "        early_going_min,\n" + "        ot_calculated_time,\n" + "        ot_calculated_after_hr,\n" + "        CONCAT(FLOOR(shift_ot_hour/60),\n" + "        ':',\n" + "        LPAD(MOD(shift_ot_hour,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_ot_hour,\n" + "        self_group_id,\n" + "        m_self_grup.ex_int1 as status,\n" + "        department_id,\n" + "        location_id,\n" + "        short_name     \n" + "    from\n" + "        tbl_shift_timming,\n" + "        m_self_grup      \n" + "    where\n" + "        status=1 \n" + "        and m_self_grup.selft_group_id=tbl_shift_timming.self_group_id \n" + "        and id=:shiftId", nativeQuery = true)
public ShiftMaster getShiftListByShiftIdLpad(int shiftId)


@Query(value = "select\n" + "        id,\n" + "        CONCAT(shiftname,' (',m_self_grup.name,')') as shiftname,\n" + "        fromtime,\n" + "        totime,\n" + "        changeable,\n" + "        changewith,\n" + "        company_id,\n" + "        max_late_time_allowed,\n" + "        CONCAT(FLOOR(shift_hr/60),\n" + "        '.',\n" + "        LPAD(MOD(shift_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_hr,\n" + "        CONCAT(FLOOR(shift_halfday_hr/60),\n" + "        '.',\n" + "        LPAD(MOD(shift_halfday_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_halfday_hr,\n" + "        early_going_min,\n" + "        ot_calculated_time,\n" + "        ot_calculated_after_hr,\n" + "        CONCAT(FLOOR(shift_ot_hour/60),\n" + "        '.',\n" + "        LPAD(MOD(shift_ot_hour,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_ot_hour,\n" + "        self_group_id,\n" + "        status,\n" + "        department_id,\n" + "        m_self_grup.ex_int1 as location_id,short_name\n" + "    from\n" + "        tbl_shift_timming,m_self_grup \n" + "    where\n" + "          status=1 and m_self_grup.selft_group_id=tbl_shift_timming.self_group_id order by id desc", nativeQuery = true)
public List<ShiftMaster> getShiftListByLpad()


public List<ShiftMaster> findByStatus(int i)


@Query(value = "select * from tbl_shift_timming where  status=1 and self_group_id=:groupId", nativeQuery = true)
public List<ShiftMaster> getShiftListByGroupIdandlocId(int groupId)


@Query(value = "select * from tbl_shift_timming where location_id in (:locationIds) and status=1", nativeQuery = true)
public List<ShiftMaster> showShiftListByLocationIds(List<Integer> locationIds)


public List<ShiftMaster> findBySelfGroupIdAndStatus(int bonusId,int i)


@Transactional
@Modifying
@Query("update ShiftMaster set status=0  WHERE id=:shiftId")
public int deleteShiftTime(int shiftId)


@Query(value = "select\n" + "        id,\n" + "        CONCAT(shiftname,' (',m_self_grup.name,')') as shiftname,\n" + "        fromtime,\n" + "        totime,\n" + "        changeable,\n" + "        changewith,\n" + "        company_id,\n" + "        max_late_time_allowed,\n" + "        CONCAT(FLOOR(shift_hr/60),\n" + "        '.',\n" + "        LPAD(MOD(shift_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_hr,\n" + "        CONCAT(FLOOR(shift_halfday_hr/60),\n" + "        '.',\n" + "        LPAD(MOD(shift_halfday_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_halfday_hr,\n" + "        early_going_min,\n" + "        ot_calculated_time,\n" + "        ot_calculated_after_hr,\n" + "        CONCAT(FLOOR(shift_ot_hour/60),\n" + "        '.',\n" + "        LPAD(MOD(shift_ot_hour,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_ot_hour,\n" + "        self_group_id,\n" + "        m_self_grup.ex_int1 as status,\n" + "        department_id,\n" + "        location_id,short_name\n" + "    from\n" + "        tbl_shift_timming,m_self_grup \n" + "    where\n" + "          status=1 and m_self_grup.selft_group_id=tbl_shift_timming.self_group_id", nativeQuery = true)
public List<ShiftMaster> getShiftListByLpadForShiftAllocation()


}