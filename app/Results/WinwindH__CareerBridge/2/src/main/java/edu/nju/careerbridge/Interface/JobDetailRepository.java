package edu.nju.careerbridge.Interface;
public interface JobDetailRepository {

   public JobDetail findByJobId(String jobId);
}