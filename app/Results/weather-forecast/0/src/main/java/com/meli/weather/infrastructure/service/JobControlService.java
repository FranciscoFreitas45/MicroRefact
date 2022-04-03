package com.meli.weather.infrastructure.service;
 import com.meli.weather.infrastructure.model.JobControl;
import javax.inject.Singleton;
@Singleton
public class JobControlService {


public JobControl fromDomainToModel(com.meli.weather.domain.JobControl domain){
    var jobControlModel = new JobControl();
    jobControlModel.setId(domain.getId());
    jobControlModel.setErrorMessage(domain.getErrorMessage());
    jobControlModel.setStatus(domain.getStatus());
    jobControlModel.setType(domain.getType());
    jobControlModel.setExecutionDate(domain.getExecutionDate());
    return jobControlModel;
}


public com.meli.weather.domain.JobControl fromModelToDomain(JobControl model){
    return new com.meli.weather.domain.JobControl(model.getId(), model.getExecutionDate(), model.getType(), model.getStatus(), model.getErrorMessage());
}


}