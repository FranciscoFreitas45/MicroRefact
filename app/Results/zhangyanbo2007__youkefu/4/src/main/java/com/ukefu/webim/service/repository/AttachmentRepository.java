package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AttachmentFile;
public interface AttachmentRepository extends JpaRepository<AttachmentFile, String>{


public Page<AttachmentFile> findByOrgi(String orgi,Pageable page)
;

public List<AttachmentFile> findByDataidAndOrgi(String dataid,String orgi)
;

public List<AttachmentFile> findByModelidAndOrgi(String modelid,String orgi)
;

public AttachmentFile findByIdAndOrgi(String id,String orgi)
;

}