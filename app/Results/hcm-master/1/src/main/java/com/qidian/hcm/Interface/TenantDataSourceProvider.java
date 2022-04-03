package com.qidian.hcm.Interface;
public interface TenantDataSourceProvider {

   public ProcessEngine getProcessEngine();
   public Object getRepositoryService(Object Object);
   public Object createDeployment(Object Object);
   public Object name(Object Object);
   public Object addClasspathResource(Object Object);
   public Object deploy(Object Object);
   public Object createProcessDefinitionQuery(Object Object);
   public Object orderByProcessDefinitionVersion(Object Object);
   public Object asc(Object Object);
   public Object list(Object Object);
   public Object getRuntimeService(Object Object);
   public Object startProcessInstanceByKey(Object Object);
}