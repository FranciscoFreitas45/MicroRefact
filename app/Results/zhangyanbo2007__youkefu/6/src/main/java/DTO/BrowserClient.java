package DTO;
 public class BrowserClient {

 private  String useragent;

 private  String os;

 private  String browser;

 private  String version;

 private  String osversion;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getVersion(){
    return version;
}


public String getOs(){
    return os;
}


public String getUseragent(){
    return useragent;
}


public String getOsversion(){
    return osversion;
}


public String getBrowser(){
    return browser;
}


public void setUseragent(String useragent){
    this.useragent = useragent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUseragent"))

.queryParam("useragent",useragent)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOs(String os){
    this.os = os;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOs"))

.queryParam("os",os)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBrowser(String browser){
    this.browser = browser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBrowser"))

.queryParam("browser",browser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVersion(String version){
    this.version = version;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVersion"))

.queryParam("version",version)
;
restTemplate.put(builder.toUriString(),null);
}


}