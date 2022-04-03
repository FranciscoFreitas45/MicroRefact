package com.meli.weather.application.impl;
 import com.meli.weather.application.CreateJobControlInteractor;
import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.repository.JobControlRepository;
import javax.inject.Singleton;
@Singleton
public class CreateJobControlInteractorImpl implements CreateJobControlInteractor{

 private  JobControlRepository jobControlRepository;

public CreateJobControlInteractorImpl(JobControlRepository jobControlRepository) {
    this.jobControlRepository = jobControlRepository;
}
@Override
public JobControl execute(JobControl jobControl){
    return this.jobControlRepository.createJob(jobControl);
}


}