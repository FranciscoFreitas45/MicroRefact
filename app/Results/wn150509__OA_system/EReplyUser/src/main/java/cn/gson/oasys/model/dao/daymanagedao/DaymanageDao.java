package cn.gson.oasys.model.dao.daymanagedao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.gson.oasys.model.entity.schedule.ScheduleList;
import cn.gson.oasys.model.entity.user.User;
public interface DaymanageDao extends JpaRepository<ScheduleList, Long>{


public Page<ScheduleList> findByUserAndUsers(User user,List<User> users,Pageable pa)
;

public Page<ScheduleList> findByUser(User user,Pageable pa)
;

public Page<ScheduleList> findByUsers(List<User> users,Pageable pa)
;

public void setUser(Long id,User user);

public void setUsers(Long id,List<User> users);

public void setUsername(Long id,String username);

public void setIsreminded(Long id,Boolean isreminded);

}