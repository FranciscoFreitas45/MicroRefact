package es.us.isa.ideas.app.configuration;
 import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
public class StudioConfiguration implements Serializable{

 private  Logger LOG;

 protected  String CONFIG_PATH;

 protected  String DEVELOP_PATH;

 private  long serialVersionUID;

 private  String workbenchName;

 private  Map<String,String> modules;

 private  Map<String,String> images;

 private  Map<String,String> configurationFiles;

 private  String helpURI;

 private  String helpMode;

 private  String googleAnalyticsID;

 private  Boolean advancedMode;

 private  List<String> extensionsFilter;

 private  List<String> extensionsPreferences;

public StudioConfiguration() {
    super();
}
public void setExtensionsPreferences(List<String> extensionsPreferences){
    this.extensionsPreferences = extensionsPreferences;
}


public String getHelpURI(){
    return helpURI;
}


public List<String> getExtensionsPreferences(){
    return extensionsPreferences;
}


public void setModules(Map<String,String> modules){
    this.modules = modules;
}


public void setGoogleAnalyticsID(String ga){
    this.googleAnalyticsID = ga;
}


public Map<String,String> getModules(){
    return modules;
}


public String getWorkbenchName(){
    return workbenchName;
}


public List<String> getExtensionsFilter(){
    return extensionsFilter;
}


public void setExtensionsFilter(List<String> extensionsFilter){
    this.extensionsFilter = extensionsFilter;
}


public void setHelpURI(String helpURI){
    this.helpURI = helpURI;
}


public Map<String,String> getConfigurationFiles(){
    return configurationFiles;
}


public Boolean getAdvancedMode(){
    return advancedMode;
}


public void setConfigurationFiles(Map<String,String> configFiles){
    this.configurationFiles = configFiles;
}


public StudioConfiguration load(String realPath){
    StudioConfiguration config = null;
    try {
        Gson gson = new Gson();
        String json = FileUtils.readFileToString(new File(realPath + CONFIG_PATH));
        config = gson.fromJson(json, StudioConfiguration.class);
    } catch (IOException e) {
        LOG.log(Level.SEVERE, null, e);
    }
    return config;
}


public void setAdvancedMode(Boolean advancedMode){
    this.advancedMode = advancedMode;
}


public void setHelpMode(String helpMode){
    this.helpMode = helpMode;
}


public void setWorkbenchName(String workbenchName){
    this.workbenchName = workbenchName;
}


public void setImages(Map<String,String> images){
    this.images = images;
}


public String getGoogleAnalyticsID(){
    return googleAnalyticsID;
}


public String getHelpMode(){
    return helpMode;
}


public Map<String,String> getImages(){
    return images;
}


}