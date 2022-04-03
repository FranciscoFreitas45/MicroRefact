package com.ukefu.webim.service.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.NoticeMsg;
public interface NoticeMsgRepository extends JpaRepository<NoticeMsg, String>{


public int countByTargetAndStatusAndDatastatusAndOrgi(String target,boolean status,boolean datastatus,String orgi)
;

public List<NoticeMsg> findByOrgi(String orgi)
;

public List<NoticeMsg> findByNameAndOrgi(String Name,String orgi)
;

public List<NoticeMsg> findByTerracetypeAndCreatetimeBefore(String terracetype,Date createtime)
;

public NoticeMsg findByIdAndOrgi(String id,String orgi)
;

public Page<NoticeMsg> findAll(Specification<NoticeMsg> spec,Pageable page)
;

}