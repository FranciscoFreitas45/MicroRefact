package com.ushahidi.swiftriver.core.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class FormField {

 private  Long id;

 private  Form form;

 private  String title;

 private  String description;

 private  String type;

 private  Boolean required;

 private  String options;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Form getForm(){
    return form;
}


public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


public String getType(){
    return type;
}


public String getOptions(){
    return options;
}


public Boolean getRequired(){
    return required;
}


public void setForm(Form form){
    this.form = form;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setForm"))

.queryParam("form",form)
;
restTemplate.put(builder.toUriString(),null);
}


}