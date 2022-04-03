package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.OnlineUserHis;
public interface OnlineUserHisRepository extends JpaRepository<OnlineUserHis, String>{


public List<OnlineUserHis> findBySessionidAndOrgi(String paramString,String orgi)
;

public OnlineUserHis findByIdAndOrgi(String paramString,String orgi)
;

public List<OnlineUserHis> findByUseridAndOrgi(String userid,String orgi)
;

}