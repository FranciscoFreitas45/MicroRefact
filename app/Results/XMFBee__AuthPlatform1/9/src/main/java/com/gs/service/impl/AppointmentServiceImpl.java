package com.gs.service.impl;
 import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.AppointmentDAO;
import com.gs.service.AppointmentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService{

@Resource
 private  AppointmentDAO appointmentDAO;


@Override
public List<Appointment> queryByOwner(Pager pager,String userId){
    return appointmentDAO.queryByOwner(pager, userId);
}


public List<Appointment> queryByPagerDisable(Pager pager){
    return appointmentDAO.queryByPagerDisable(pager);
}


public int batchUpdate(List<Appointment> list){
    return appointmentDAO.batchUpdate(list);
}


@Override
public int batchInsert(List<Appointment> list){
    return 0;
}


@Override
public int insert(Appointment appointment){
    return appointmentDAO.insert(appointment);
}


public int update(Appointment appointment){
    return appointmentDAO.update(appointment);
}


@Override
public int countByCurrentStatus(User user){
    return appointmentDAO.countByCurrentStatus(user);
}


@Override
public List<Appointment> queryAll(String status){
    return appointmentDAO.queryAll(status);
}


public int delete(Appointment appointment){
    return appointmentDAO.delete(appointment);
}


@Override
public int countByOwner(String userId){
    return appointmentDAO.countByOwner(userId);
}


public int batchDelete(List<Appointment> list){
    return appointmentDAO.batchDelete(list);
}


@Override
public List<Appointment> queryByPhone(String userPhone){
    return appointmentDAO.queryByPhone(userPhone);
}


public int inactive(String id){
    return appointmentDAO.inactive(id);
}


@Override
public int countByBlurred(Appointment appointment,User user){
    return appointmentDAO.countByBlurred(appointment, user);
}


@Override
public List<Appointment> queryByCompanyId(String companyId){
    return appointmentDAO.queryByCompanyId(companyId);
}


public Appointment query(Appointment appointment){
    return appointmentDAO.query(appointment);
}


@Override
public int count(User user){
    return appointmentDAO.count(user);
}


public int active(String id){
    return appointmentDAO.active(id);
}


@Override
public List<Appointment> queryByCurrentStatus(Pager pager){
    return appointmentDAO.queryByCurrentStatus(pager);
}


public List<Appointment> blurredQuery(Pager pager,Appointment appointment){
    return appointmentDAO.blurredQuery(pager, appointment);
}


public List<Appointment> queryByStatus(String status){
    return appointmentDAO.queryAll(status);
}


@Override
public void updateUserIds(String userId,String appIds){
    appointmentDAO.updateUserIds(userId, appIds);
}


public int deleteById(String id){
    return appointmentDAO.deleteById(id);
}


@Override
public int countByDisable(User user){
    return appointmentDAO.countByDisable(user);
}


@Override
public void updateCurrentById(String currentStatus,String appointmentId){
    appointmentDAO.updateCurrentById(currentStatus, appointmentId);
}


public Appointment queryById(String id){
    return appointmentDAO.queryById(id);
}


public List<Appointment> queryByPager(Pager pager){
    return appointmentDAO.queryByPager(pager);
}


}