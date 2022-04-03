package DTO;
 import java.io.StringWriter;
import java.util.Map;
import org.jeecgframework.core.util.ApplicationContextUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveModel;
public class FreemarkerHelper {

 private  Configuration _tplConfig;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String parseTemplate(String tplName,Map<String,Object> paras){
    return this.parseTemplate(tplName, "utf-8", paras);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parseTemplate"))

.queryParam("tplName",tplName)
.queryParam("paras",paras)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}