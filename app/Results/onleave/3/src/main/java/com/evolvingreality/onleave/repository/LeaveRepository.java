package com.evolvingreality.onleave.repository;
 import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.LeaveStatus;
import com.evolvingreality.onleave.model.LeaveType;
import com.evolvingreality.onleave.model.User;
public interface LeaveRepository extends JpaRepository<Leave, Long>{


public List<Leave> findByUserAndStartDateTimeBetweenAndLeaveTypeInAndLeaveStatusInOrderByStartDateTimeDesc(User user,Date startDate,Date endDate,Collection<LeaveType> leaveTypes,Collection<LeaveStatus> leaveStatuses)
;

public Page<Leave> findByUserAndLeaveTypeInOrderByStartDateTimeDesc(User user,Collection<LeaveType> leaveTypes,Pageable pageable)
;

}