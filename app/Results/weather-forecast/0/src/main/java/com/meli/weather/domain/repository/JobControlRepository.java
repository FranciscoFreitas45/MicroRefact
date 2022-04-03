package com.meli.weather.domain.repository;
 import com.meli.weather.domain.JobControl;
import com.meli.weather.domain.JobTypeEnum;
public interface JobControlRepository {


public JobControl findJobByType(JobTypeEnum type)
;

public JobControl updateJob(JobControl jobControl)
;

public JobControl createJob(JobControl jobControl)
;

}