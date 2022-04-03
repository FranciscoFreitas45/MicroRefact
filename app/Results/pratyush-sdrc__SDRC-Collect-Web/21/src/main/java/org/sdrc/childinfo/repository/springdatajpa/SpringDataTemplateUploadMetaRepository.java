package org.sdrc.childinfo.repository.springdatajpa;
 import org.sdrc.childinfo.domain.TemplateUploadMeta;
import org.sdrc.childinfo.repository.TemplateUploadMetaRepository;
import org.springframework.data.repository.Repository;
public interface SpringDataTemplateUploadMetaRepository extends TemplateUploadMetaRepository, Repository<TemplateUploadMeta, Integer>{


public void setDateOfUpload(Integer id,Timestamp dateOfUpload);

public void setUserIp(Integer id,String userIp);

public void setDataProvidedBy(Integer id,String dataProvidedBy);

}