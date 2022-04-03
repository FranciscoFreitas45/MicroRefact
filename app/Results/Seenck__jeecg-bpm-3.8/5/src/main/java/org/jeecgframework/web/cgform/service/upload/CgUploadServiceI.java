package org.jeecgframework.web.cgform.service.upload;
 import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
public interface CgUploadServiceI extends CommonService{


public void deleteFile(CgUploadEntity file)
;

public void writeBack(String cgFormId,String cgFormName,String cgFormField,String fileId,String fileUrl)
;

}