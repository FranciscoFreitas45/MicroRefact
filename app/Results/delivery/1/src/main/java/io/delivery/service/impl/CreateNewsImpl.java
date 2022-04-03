package io.delivery.service.impl;
 import io.delivery.model.Answer;
import io.delivery.service.CreateNews;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
public class CreateNewsImpl implements CreateNews{

 private  Logger LOG;

@Autowired
 private  Answer answer;

 private  String query;


@Override
public String createNews(){
    LOG.info("our query: " + query);
    return query;
}


public void setQuery(String query){
    this.query = query;
}


public String getQuery(){
    return query;
}


}