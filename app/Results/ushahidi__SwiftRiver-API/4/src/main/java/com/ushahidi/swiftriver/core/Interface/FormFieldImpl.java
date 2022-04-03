package com.ushahidi.swiftriver.core.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.Interface.FormField;
public class FormFieldImpl implements FormField{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}