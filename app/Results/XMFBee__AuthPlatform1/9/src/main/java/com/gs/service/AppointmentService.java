package com.gs.service;
 import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import java.util.List;
public interface AppointmentService extends BaseService<String, Appointment>{


public List<Appointment> queryByPhone(String userPhone)
;

public List<Appointment> queryByOwner(Pager pager,String userId)
;

public void updateUserIds(String userId,String appIds)
;

public int countByCurrentStatus(User user)
;

public List<Appointment> queryByCurrentStatus(Pager pager)
;

public List<Appointment> queryByCompanyId(String companyId)
;

public void updateCurrentById(String currentStatus,String appointmentId)
;

public int countByOwner(String userId)
;

}