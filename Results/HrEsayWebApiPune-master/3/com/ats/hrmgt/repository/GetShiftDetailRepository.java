import com.ats.hrmgt.model.GetShiftDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface GetShiftDetailRepository extends JpaRepository<GetShiftDetail, Integer> {


@Query(value = " select\n" + "        id,\n" + "        shiftname as shiftname,\n" + "        fromtime,\n" + "        totime,\n" + "        changeable,\n" + "        changewith,\n" + "        company_id,\n" + "        max_late_time_allowed,\n" + "        CONCAT(FLOOR(shift_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(shift_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_hr,\n" + "        CONCAT(FLOOR(shift_halfday_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(shift_halfday_hr,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_halfday_hr,\n" + "        early_going_min,\n" + "        ot_calculated_time,\n" + "        ot_calculated_after_hr,\n" + "        CONCAT(FLOOR(shift_ot_hour/60),\n" + "        ':',\n" + "        LPAD(MOD(shift_ot_hour,\n" + "        60),\n" + "        2,\n" + "        '0')) as shift_ot_hour,\n" + "        self_group_id,\n" + "        m_self_grup.ex_int1 as status,\n" + "        department_id,\n" + "        location_id,\n" + "        short_name     \n" + "    from\n" + "        tbl_shift_timming,\n" + "        m_self_grup      \n" + "    where\n" + "        status=1 \n" + "        and m_self_grup.selft_group_id=tbl_shift_timming.self_group_id \n" + "        and id=:shiftId", nativeQuery = true)
public GetShiftDetail getShiftListByShiftIdLpad(int shiftId)


}