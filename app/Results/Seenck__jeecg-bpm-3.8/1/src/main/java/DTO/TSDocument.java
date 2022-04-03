package DTO;
 import javax.persistence;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSType;
public class TSDocument extends TSAttachment{

 private  String documentTitle;

 private  byte[] pictureIndex;

 private  Short documentState;

 private  Short showHome;

 private  TSType TSType;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Column(name = "showhome")
public Short getShowHome(){
    return showHome;
}


@Column(name = "documenttitle", length = 100)
public String getDocumentTitle(){
    return documentTitle;
}


@Column(name = "pictureindex", length = 3000)
public byte[] getPictureIndex(){
    return pictureIndex;
}


@Column(name = "documentstate")
public Short getDocumentState(){
    return documentState;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "typeid")
public TSType getTSType(){
    return TSType;
}


public void setDocumentTitle(String documentTitle){
    this.documentTitle = documentTitle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDocumentTitle"))

.queryParam("documentTitle",documentTitle)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTSType(TSType tSType){
    TSType = tSType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSType"))

.queryParam("tSType",tSType)
;
restTemplate.put(builder.toUriString(),null);
}


}