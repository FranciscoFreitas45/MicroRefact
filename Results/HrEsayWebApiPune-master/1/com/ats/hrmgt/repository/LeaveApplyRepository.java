import com.ats.hrmgt.model.LeaveApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveApplyRepository extends JpaRepository<LeaveApply, Integer> {


@Transactional
@Modifying
@Query("update LeaveApply set leave_num_days=leave_num_days-:updateNoOfDays,leave_cancle_remark=:reason  WHERE leave_id=:leaveId")
public int updateNoOfDaysInLeave(int leaveId,float updateNoOfDays,String reason)


@Transactional
@Modifying
@Query("update LeaveApply set ex_int1=:status  WHERE leave_id=:leaveId")
public int updateLeaveStatus(int leaveId,int status)


@Transactional
@Modifying
@Query("update LeaveApply set del_status=0  WHERE leave_id=:leaveId")
public int deleteLeaveApply(int leaveId)


@Query(value = "select * from leave_apply where ((leave_fromdt between :fromDate and :toDate) or " + " (leave_todt between :fromDate and :toDate) ) and ex_int1=3 and emp_id=:empId", nativeQuery = true)
public List<LeaveApply> getleavetList(String fromDate,String toDate,int empId)


@Transactional
@Modifying
@Query("update LeaveApply set ex_int2=:trailId  WHERE leave_id=:leaveId")
public int updateLeaveApply(int leaveId,int trailId)


@Query(value = "SELECT * FROM leave_apply WHERE ((:fromDate between leave_fromdt and leave_todt) or (:toDate between leave_fromdt and leave_todt) or " + "(leave_fromdt between :fromDate and :toDate)or (leave_todt between :fromDate and :toDate)) and ex_int1 in (1,2,3) and emp_id=:empId", nativeQuery = true)
public List<LeaveApply> checkDateForRepetedLeaveValidation(String fromDate,String toDate,int empId)


@Query(value = "select l.leave_id,l.cal_yr_id,l.emp_id,l.lv_type_id,l.leave_duration,l.leave_fromdt,l.leave_todt,l.leave_num_days,l.leave_emp_reason,l.final_status,l.del_status," + "l.circulated_to,l.is_active,l.maker_user_id,l.maker_enter_datetime,l.ex_int1,l.ex_int2,l.ex_int3,lt.lv_title_short as ex_var1,lt.lv_sumup_id as ex_var2," + "l.ex_var3,l.status,l.leave_cancle_remark,l.lvt_application_id_parent,l.rec_status from leave_apply l,leave_type lt where " + "((:fromDate between  l.leave_fromdt  and  l.leave_todt) or ( :toDate between  l.leave_fromdt  and  l.leave_todt ) or ( l.leave_fromdt between :fromDate and :toDate ) or ( l.leave_todt between :fromDate and :toDate )) and l.ex_int1=3 and lt.lv_type_id=l.lv_type_id and emp_id=:empId", nativeQuery = true)
public List<LeaveApply> getleavetListForAttndace(String fromDate,String toDate,int empId)


@Transactional
@Modifying
@Query("delete from LeaveApply where leave_id=:leaveId")
public int deleteByLeaveId(int leaveId)


@Query(value = "SELECT * FROM leave_apply WHERE ((DATE(:fromDate + INTERVAL 1 DAY) between leave_fromdt and leave_todt) or (DATE(:toDate- INTERVAL 1 DAY) between " + "leave_fromdt and leave_todt))  and ex_int1 in (1,2,3) and emp_id=:empId and lv_type_id=:leaveTypeId and leave_num_days!=0", nativeQuery = true)
public List<LeaveApply> checkContinueDateLeave(String fromDate,String toDate,int empId,int leaveTypeId)


public List<LeaveApply> findByDelStatus(int i)


public LeaveApply findByLeaveIdAndDelStatus(int leaveId,int i)


@Transactional
@Modifying
@Query("update LeaveApply set leave_num_days=leave_num_days+:updateNoOfDays,leave_cancle_remark=:reason  WHERE leave_id=:leaveId")
public int reverseupdateNoOfDaysInLeave(int leaveId,float updateNoOfDays,String reason)


@Query(value = "select * from leave_apply where leave_fromdt between :fromDate and :toDate and ex_int1=7 and lvt_application_id_parent!=0 and emp_id=:empId", nativeQuery = true)
public List<LeaveApply> leaveListAddeBySystem(String fromDate,String toDate,int empId)


public List<LeaveApply> findByCalYrIdAndDelStatusAndEmpId(int calYr,int i,int empId)


}