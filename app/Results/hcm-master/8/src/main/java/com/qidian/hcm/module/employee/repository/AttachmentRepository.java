package com.qidian.hcm.module.employee.repository;
 import com.qidian.hcm.module.employee.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AttachmentRepository extends JpaRepository<Attachment, Long>{


public Optional<Attachment> findByFileId(Long fileId)
;

}