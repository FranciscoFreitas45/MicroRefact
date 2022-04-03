package com.meli.weather.application.impl;
 import com.meli.weather.application.UpdateJobControlInteractor;
import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.repository.JobControlRepository;
import javax.inject.Singleton;
@Singleton
public class UpdateJobControlInteractorImpl implements UpdateJobControlInteractor{

 private  JobControlRepository jobControlRepository;

public UpdateJobControlInteractorImpl(JobControlRepository jobControlRepository) {
    this.jobControlRepository = jobControlRepository;
}
@Override
public JobControl execute(JobControl jobControl){
    return this.jobControlRepository.updateJob(jobControl);
}


}