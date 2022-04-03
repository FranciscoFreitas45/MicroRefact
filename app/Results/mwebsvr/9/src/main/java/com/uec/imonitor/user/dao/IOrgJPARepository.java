package com.uec.imonitor.user.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.uec.imonitor.user.bean.OrgEntity;
public interface IOrgJPARepository extends JpaRepository<OrgEntity, Integer>{


}