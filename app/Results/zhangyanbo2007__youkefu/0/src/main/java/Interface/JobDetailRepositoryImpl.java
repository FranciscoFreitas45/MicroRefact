package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.JobDetailRepository;
public class JobDetailRepositoryImpl implements JobDetailRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Page<JobDetail> findByTaskstatus(String taskstatus,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTaskstatus"))
    .queryParam("taskstatus",taskstatus)
    .queryParam("page",page)
;  Page<JobDetail> aux = restTemplate.getForObject(builder.toUriString(), Page<JobDetail>.class);

 return aux;
}


public Page<JobDetail> findByPlantaskAndTaskstatusAndNextfiretimeLessThan(boolean plantask,String taskstatus,Date time,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPlantaskAndTaskstatusAndNextfiretimeLessThan"))
    .queryParam("plantask",plantask)
    .queryParam("taskstatus",taskstatus)
    .queryParam("time",time)
    .queryParam("page",page)
;  Page<JobDetail> aux = restTemplate.getForObject(builder.toUriString(), Page<JobDetail>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}