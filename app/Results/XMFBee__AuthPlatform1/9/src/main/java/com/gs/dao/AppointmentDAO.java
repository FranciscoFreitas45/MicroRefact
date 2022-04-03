package com.gs.dao;
 import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AppointmentDAO extends BaseDAO<String, Appointment>{


public List<Appointment> queryByPhone(String userPhone)
;

public List<Appointment> queryByOwner(Pager pager,String userId)
;

public void updateUserIds(String userId,String appIds)
;

public int countByBlurred(Appointment appointment,User user)
;

public int countByCurrentStatus(User user)
;

public List<Appointment> queryByCurrentStatus(Pager pager)
;

public List<Appointment> queryByCompanyId(String companyId)
;

public List<Appointment> blurredQuery(Pager pager,Appointment appointment)
;

public void updateCurrentById(String currentStatus,String appointmentId)
;

public int countByOwner(String userId)
;

}