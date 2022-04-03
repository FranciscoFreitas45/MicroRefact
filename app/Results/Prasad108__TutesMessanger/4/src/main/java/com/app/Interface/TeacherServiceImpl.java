package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.TeacherService;
public class TeacherServiceImpl implements TeacherService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void create(Teacher teacher){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/create"))
    .queryParam("teacher",teacher)
;
  restTemplate.put(builder.toUriString(), null);
}


}