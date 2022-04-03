package org.jeecgframework.web.cgform.dao.upload;
 import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.springframework.stereotype.Repository;
@Repository("cgFormUploadDao")
public interface CgFormUploadDao {


@Arguments({ "cgFormId", "cgFormName", "cgFormField", "fileId", "fileUrl" })
public void updateBackFileInfo(String cgFormId,String cgFormName,String cgFormField,String fileId,String fileUrl)
;

}