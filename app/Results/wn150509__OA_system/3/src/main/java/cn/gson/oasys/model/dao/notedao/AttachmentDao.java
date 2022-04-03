package cn.gson.oasys.model.dao.notedao;
 import java.util.Date;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import cn.gson.oasys.model.entity.note.Attachment;
import cn.gson.oasys.model.entity.note.Note;
@Repository
public interface AttachmentDao extends JpaRepository<Attachment, Long>{


public Attachment findByAttachmentId(long AttachmentId)
;

@Query("update Attachment a set a.attachmentName=?1,a.attachmentPath=?2,a.attachmentShuffix=?3,a.attachmentSize=?4,a.attachmentType=?5,a.uploadTime=?6 where a.attachmentId=?7")
@Modifying
public Integer updateatt(String attname,String attpath,String shu,Long size,String type,Date uptime,Long attid)
;

public Attachment findByAttachmentPath(String AttachmentPath)
;

public Attachment getProFileid(Long attachmentId);

public void setProFileid(Long attachmentId,Attachment proFileid);

public void setModel(Long id,String model);

public void setAttachmentPath(Long id,String attachmentPath);

public void setAttachmentShuffix(Long id,String attachmentShuffix);

public void setAttachmentSize(Long id,Long attachmentSize);

public void setAttachmentType(Long id,String attachmentType);

public void setUploadTime(Long id,Date uploadTime);

public void setUserId(Long id,String userId);

}