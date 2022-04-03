package com.meli.weather.fixtures;
 import java.lang;
import java.util;
import java.io;
import java.net;
import groovy.lang;
import groovy.util;
@groovy.transform.Trait()
public interface JobControlFixtures {


public com.meli.weather.domain.JobControl jobDomainFromModel(com.meli.weather.infrastructure.model.JobControl jobModel)
;

public com.meli.weather.domain.JobControl startJobControlDomain(com.meli.weather.domain.JobTypeEnum type)
;

public com.meli.weather.infrastructure.model.JobControl getJobControlModelWithId(java.lang.String id)
;

public com.meli.weather.infrastructure.model.JobControl getJobControlModel()
;

}