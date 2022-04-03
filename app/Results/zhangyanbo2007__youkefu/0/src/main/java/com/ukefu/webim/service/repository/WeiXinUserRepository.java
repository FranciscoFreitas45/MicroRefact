package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.WeiXinUser;
public interface WeiXinUserRepository extends JpaRepository<WeiXinUser, String>{


public long countByOpenidAndOrgi(String openid,String orgi)
;

public Page<WeiXinUser> findBySnsidAndOrgi(String snsid,String orgi,Pageable page)
;

public List<WeiXinUser> findByOpenidAndOrgi(String openid,String orgi)
;

public WeiXinUser findByIdAndOrgi(String id,String orgi)
;

public long countBySnsidAndOrgi(String snsid,String orgi)
;

}