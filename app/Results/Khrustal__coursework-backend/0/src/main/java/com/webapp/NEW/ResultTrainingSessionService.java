package com.webapp.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.repository.ResultRepository;
import com.webapp.models.Result;
@Service
public class ResultTrainingSessionService {

@Autowired
 private ResultRepository resultrepository;


public List<Result> getResults(Long id){
return resultrepository.getResults(id);
}


public void setResults(Long id,List<Result> results){
resultrepository.setResults(id,results);
}


}