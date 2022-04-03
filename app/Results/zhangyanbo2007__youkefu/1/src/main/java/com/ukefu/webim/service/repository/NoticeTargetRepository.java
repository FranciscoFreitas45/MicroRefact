package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.NoticeTarget;
public interface NoticeTargetRepository extends JpaRepository<NoticeTarget, String>{


public NoticeTarget findByNoticeidAndTargetAndOrgi(String noticeid,String target,String orgi)
;

public List<NoticeTarget> findByNoticeidAndCheckedAndOrgi(String noticeid,boolean checked,String orgi)
;

public List<NoticeTarget> findByOrgi(String orgi)
;

public List<NoticeTarget> findByNameAndOrgi(String name,String orgi)
;

public List<NoticeTarget> findByNoticeidAndTargettypeAndOrgi(String noticeid,String targettype,String orgi)
;

public List<NoticeTarget> findByNoticeidAndOrgi(String noticeid,String orgi)
;

public NoticeTarget findByIdAndOrgi(String id,String orgi)
;

}