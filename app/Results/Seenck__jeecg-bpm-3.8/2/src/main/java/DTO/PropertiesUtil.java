package DTO;
 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
public class PropertiesUtil {

 private  String properiesName;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public PropertiesUtil() {
}public PropertiesUtil(String fileName) {
    this.properiesName = fileName;
}
public Properties getProperties(){
    Properties p = new Properties();
    InputStream is = null;
    try {
        is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
        p.load(is);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    return p;
}


public String readProperty(String key){
    String value = "";
    InputStream is = null;
    try {
        is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
        Properties p = new Properties();
        p.load(is);
        value = p.getProperty(key);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    return value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/readProperty"))

.queryParam("key",key)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}