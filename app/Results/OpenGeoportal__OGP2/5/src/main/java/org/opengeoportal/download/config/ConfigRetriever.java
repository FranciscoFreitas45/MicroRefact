package org.opengeoportal.download.config;
 import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ConfigRetriever {

 public  JsonNode configContents;

 private Resource resource;

 protected  Logger logger;


public void readConfigFile(){
    if (configContents == null) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(this.loadConfigFile(), JsonNode.class);
        JsonNode jsonResponse = rootNode.path("config");
        this.configContents = jsonResponse;
    }
}


public File loadConfigFile(){
    File configFile = resource.getFile();
    return configFile;
}


public void setResource(Resource resource){
    this.resource = resource;
}


}