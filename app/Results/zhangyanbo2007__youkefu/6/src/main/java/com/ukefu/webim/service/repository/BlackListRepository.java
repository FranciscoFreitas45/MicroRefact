package com.ukefu.webim.service.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.BlackEntity;
public interface BlackListRepository extends JpaRepository<BlackEntity, String>{


public int countByPhoneAndOrgi(String phone,String orgi)
;

public List<BlackEntity> findByOrgi(String orgi)
;

public Page<BlackEntity> findByEndtimeLessThan(Date endtime,Pageable page)
;

public BlackEntity findByIdAndOrgi(String id,String orgi)
;

public BlackEntity findByUseridAndOrgi(String userid,String orgi)
;

}