package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
@Repository
public interface AttachmentDao extends JpaRepository<Attachment, Long>{


}