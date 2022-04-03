package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.notedao.AttachmentDao;
import cn.gson.oasys.model.entity.note.Attachment;
@Service
public class AttachmentProcessListService {

@Autowired
 private AttachmentDao attachmentdao;


public Attachment getProFileid(Long attachmentId){
return attachmentdao.getProFileid(attachmentId);
}


public void setProFileid(Long attachmentId,Attachment proFileid){
attachmentdao.setProFileid(attachmentId,proFileid);
}


}