package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class QualityConfig {

 private  long serialVersionUID;

 private  String id;

 private  boolean phonetic;

 private  String engine;

 private  String appid;

 private  String secretkey;

 private  String lfasrhost;

 private  String filepiecesize;

 private  String storepath;

 private  int maxthreads;

 private  String creater;

 private  Date createtime;

 private  String updater;

 private  Date updatetime;

 private  String orgi;

 private  int archivetime;

 private  int aplarchivetime;

 private  boolean phonetrans;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getFilepiecesize(){
    return filepiecesize;
}


public String getStorepath(){
    return storepath;
}


public String getUpdater(){
    return updater;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public int getAplarchivetime(){
    return aplarchivetime;
}


public String getLfasrhost(){
    return lfasrhost;
}


public Date getCreatetime(){
    return createtime;
}


public String getEngine(){
    return engine;
}


public String getCreater(){
    return creater;
}


public String getSecretkey(){
    return secretkey;
}


public Date getUpdatetime(){
    return updatetime;
}


public int getArchivetime(){
    return archivetime;
}


public String getAppid(){
    return appid;
}


public String getOrgi(){
    return orgi;
}


public int getMaxthreads(){
    return maxthreads;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setArchivetime(int archivetime){
    this.archivetime = archivetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setArchivetime"))

.queryParam("archivetime",archivetime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAplarchivetime(int aplarchivetime){
    this.aplarchivetime = aplarchivetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAplarchivetime"))

.queryParam("aplarchivetime",aplarchivetime)
;
restTemplate.put(builder.toUriString(),null);
}


}