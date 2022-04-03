package com.evolvingreality.onleave.service;
 import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.LeaveStatus;
import com.evolvingreality.onleave.model.LeaveType;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.LeaveRepository;
import com.evolvingreality.onleave.service.util.DateTimeUtil;
@Service
@Transactional(readOnly = true)
public class LeaveServiceImpl extends EntityServiceImpl<Leave>implements LeaveService{

 private  LeaveRepository leaveRepository;

@Autowired
public LeaveServiceImpl(final LeaveRepository leaveRepository) {
    super(leaveRepository);
    this.leaveRepository = leaveRepository;
}
@Override
public Page<Leave> getAnnualLeaveForYear(User user,Integer year,Pageable pageable){
    DateTime startDateTime = DateTimeUtil.getStartOfYear(year);
    DateTime endDateTime = DateTimeUtil.getEndOfYear(year);
    Collection<LeaveType> leaveTypes = new HashSet<>();
    leaveTypes.add(LeaveType.ANNUAL);
    Collection<LeaveStatus> leaveStatuses = EnumSet.<LeaveStatus>allOf(LeaveStatus.class);
    return leaveRepository.findByUserAndStartDateTimeBetweenAndLeaveTypeInAndLeaveStatusInOrderByStartDateTimeDesc(user, startDateTime.toDate(), endDateTime.toDate(), leaveTypes, leaveStatuses, pageable);
}


@Override
public Boolean hasAnnualLeaveRequestAlready(User user,Leave leave){
    Collection<LeaveType> leaveTypes = new HashSet<>();
    leaveTypes.add(LeaveType.ANNUAL);
    Collection<LeaveStatus> leaveStatuses = new HashSet<>();
    leaveStatuses.add(LeaveStatus.APPROVED);
    leaveStatuses.add(LeaveStatus.REQUESTED);
    return !leaveRepository.findByUserAndStartDateTimeBetweenAndLeaveTypeInAndLeaveStatusInOrderByStartDateTimeDesc(user, leave.getStartDateTime().toDate(), leave.getEndDateTime().toDate(), leaveTypes, leaveStatuses).isEmpty();
}


@Override
public Page<Leave> getAnnualLeaveForUser(User user,Pageable pageable){
    return leaveRepository.findByUserAndLeaveTypeInOrderByStartDateTimeDesc(user, Collections.singletonList(LeaveType.ANNUAL), pageable);
}


}