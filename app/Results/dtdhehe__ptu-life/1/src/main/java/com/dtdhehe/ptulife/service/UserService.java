package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.vo.UserRegistVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface UserService {


public String getUserNameByUserId(String userId)
;

public List<PtuUser> findTeaAll()
;

public List<PtuUser> findStuAll()
;

public PtuUser save(UserRegistVO userRegistVO)
;

public PtuUser findOne(HttpServletRequest request)
;

public PtuUser findByUserId(String userId)
;

public PtuUser update(PtuUser ptuUser)
;

public List<PtuUser> findWorAll()
;

public Page<PtuUser> findAll(Pageable pageable)
;

public PtuUser findByUserNameAndUserPwd(String userName,String userPwd)
;

}