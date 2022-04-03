package edu.nju.careerbridge.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.nju.careerbridge.Interface.JobDetailRepository;
public class JobDetailRepositoryImpl implements JobDetailRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public JobDetail findByJobId(String jobId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByJobId"))
    .queryParam("jobId",jobId)
;  JobDetail aux = restTemplate.getForObject(builder.toUriString(), JobDetail.class);

 return aux;
}


}