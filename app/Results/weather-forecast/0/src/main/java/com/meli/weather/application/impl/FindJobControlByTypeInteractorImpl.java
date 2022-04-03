package com.meli.weather.application.impl;
 import com.meli.weather.application.FindJobControlByTypeInteractor;
import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.JobTypeEnum;
import com.meli.weather.domain.repository.JobControlRepository;
import javax.inject.Singleton;
@Singleton
public class FindJobControlByTypeInteractorImpl implements FindJobControlByTypeInteractor{

 private  JobControlRepository jobControlRepository;

public FindJobControlByTypeInteractorImpl(JobControlRepository jobControlRepository) {
    this.jobControlRepository = jobControlRepository;
}
@Override
public JobControl execute(JobTypeEnum type){
    return this.jobControlRepository.findJobByType(type);
}


}