package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.CourtAnnouncementProcessor;
public class CourtAnnouncementProcessorImpl implements CourtAnnouncementProcessor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}