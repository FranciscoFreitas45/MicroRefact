package net.shangtech.weixin.appointment.service;
 import java.util.List;
import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.ssh.core.base.Page;
import net.shangtech.weixin.appointment.dao.AppointmentDao;
import net.shangtech.weixin.appointment.dao.AppointmentHistoryDao;
import net.shangtech.weixin.appointment.entity.Appointment;
import net.shangtech.weixin.appointment.entity.AppointmentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.shangtech.Interface.AppointmentDao;
import net.shangtech.Interface.AppointmentHistoryDao;
import net.shangtech.DTO.Page;
@Service
@Transactional
public class AppointmentService extends BaseService<Appointment>{

@Autowired
 private  AppointmentDao dao;

@Autowired
 private  AppointmentHistoryDao historyDao;


public Page<Appointment> findAppointmentByPage(int pageNo,int pageSize,int sysUserId){
    Page<Appointment> page = new Page<Appointment>(pageSize);
    page.setPageNo(pageNo);
    page.setQuery("where sysUserId=? order by createTime desc", sysUserId);
    page = super.find(page);
    return page;
}


@Override
public BaseDao<Appointment> dao(){
    return dao;
}


public void saveAppointment(Appointment appointment){
}


public void saveAppointmentHistory(AppointmentHistory history){
    historyDao.insert(history);
}


public List<Appointment> findBySysUserId(int sysUserId){
    return dao.find("where sysUserId=? order by createTime desc", sysUserId);
}


}