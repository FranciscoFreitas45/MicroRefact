package com.evolvingreality.onleave.service;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.User;
public interface LeaveService extends EntityService<Leave>{


public Page<Leave> getAnnualLeaveForYear(User user,Integer year,Pageable pageable)
;

public Boolean hasAnnualLeaveRequestAlready(User user,Leave leave)
;

public Page<Leave> getAnnualLeaveForUser(User user,Pageable pageable)
;

}