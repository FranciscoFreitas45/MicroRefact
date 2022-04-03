package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SNSAccount;
public interface SNSAccountRepository extends JpaRepository<SNSAccount, String>{


public int countByAppkeyAndOrgi(String appkey,String orgi)
;

public Page<SNSAccount> findBySnstypeAndOrgi(String paramString,String orgi,Pageable page)
;

public SNSAccount findBySnsid(String snsid)
;

public SNSAccount findBySnsidAndOrgi(String snsid,String orgi)
;

public List<SNSAccount> findBySnstype(String snsType)
;

public SNSAccount findByIdAndOrgi(String paramString,String orgi)
;

public int countBySnsidAndOrgi(String snsid,String orgi)
;

}