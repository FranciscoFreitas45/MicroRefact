package com.meli.weather.infrastructure.repository;
 import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.JobTypeEnum;
import com.meli.weather.domain.repository.JobControlRepository;
import com.meli.weather.infrastructure.service.JobControlService;
import javax.inject.Singleton;
@Singleton
public class JobControlRepositoryImpl implements JobControlRepository{

 private  JobControlJpaRepository jpaRepository;

 private  JobControlService jobControlService;

public JobControlRepositoryImpl(JobControlJpaRepository jpaRepository, JobControlService jobControlService) {
    this.jpaRepository = jpaRepository;
    this.jobControlService = jobControlService;
}
@Override
public JobControl findJobByType(JobTypeEnum type){
    return jpaRepository.findByType(type).map(jobControl -> jobControlService.fromModelToDomain(jobControl)).orElse(null);
}


@Override
public JobControl updateJob(JobControl jobControl){
    return jobControlService.fromModelToDomain(jpaRepository.update(jobControlService.fromDomainToModel(jobControl)));
}


@Override
public JobControl createJob(JobControl jobControl){
    return jobControlService.fromModelToDomain(jpaRepository.save(jobControlService.fromDomainToModel(jobControl)));
}


}