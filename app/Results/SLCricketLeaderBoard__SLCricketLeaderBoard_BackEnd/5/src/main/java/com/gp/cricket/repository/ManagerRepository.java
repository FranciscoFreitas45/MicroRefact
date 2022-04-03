package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Club;
import com.gp.cricket.entity.Manager;
import com.gp.cricket.entity.User;
public interface ManagerRepository extends JpaRepository<Manager, Integer>{


@Query("FROM Manager m WHERE m.userId = :userId")
public Manager getManager(User user)
;

@Query("FROM Manager m WHERE  m.userId.status = 1  AND (m.managerId NOT IN (SELECT c.managerId FROM Club c))")
public List<Manager> getAvailableManagers()
;

@Query("FROM Manager m WHERE  m.userId.status = 0")
public List<Manager> getRequestedManagers()
;

@Query("FROM Manager m WHERE  m.userId.status = 1")
public List<Manager> getAcceptedManagers()
;

public Manager getManagerId(Integer managerIdv2);

public void setManagerId(Integer managerIdv2,Manager managerId);

}