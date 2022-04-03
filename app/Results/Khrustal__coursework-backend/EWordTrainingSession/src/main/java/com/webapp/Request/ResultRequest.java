package com.webapp.Request;
import com.webapp.DTO.Result;
public interface ResultRequest {

   public List<Result> getResults(Long id);
   public void setResults(List<Result> results,Long id);
}