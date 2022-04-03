package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
public class Image extends Question{

 private  long serialVersionUID;

 private  Integer scale;

 private  String align;

 private  String url;

 private  Integer width;

 private  String longdesc;

 private  String filename;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";

public Image(String text, String uid) {
    setTitle(text);
    setUniqueId(uid);
}public Image() {
}
@Column(name = "ALIGN")
public String getAlign(){
    return align;
}


@Column(name = "IM_WIDTH")
public Integer getWidth(){
    return width;
}


@Column(name = "URL")
public String getUrl(){
    return url;
}


@Transient
public String getFilename(){
    return filename;
}


@Column(name = "SCALE")
public Integer getScale(){
    return scale;
}


@Column(name = "LONGDESC")
public String getLongdesc(){
    return longdesc;
}


public void setUrl(String url){
    this.url = url;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUrl"))

.queryParam("url",url)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFilename(String filename){
    this.filename = filename;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilename"))

.queryParam("filename",filename)
;
restTemplate.put(builder.toUriString(),null);
}


}