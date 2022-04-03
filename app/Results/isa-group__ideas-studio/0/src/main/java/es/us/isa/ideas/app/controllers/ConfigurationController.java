package es.us.isa.ideas.app.controllers;
 import com.google.common.base.Strings;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import es.us.isa.ideas.app.configuration.StudioConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
@Controller
@RequestMapping("/module")
public class ConfigurationController extends AbstractController{

 protected  String CONFIG_PATH;

 protected  String DEVELOP_PATH;

 public  String MODULES_PORT;

 private  Logger LOG;

@Autowired
 private StudioConfiguration studioConfiguration;

@Autowired
 private ServletContext servletContext;


@RequestMapping(value = "/configuration", method = RequestMethod.GET)
@ResponseBody
public StudioConfiguration getConfiguration(HttpServletRequest request){
    // Select configuration path by order preference
    String finalPath = "";
    List<String> paths = new ArrayList<>();
    paths.add(DEVELOP_PATH);
    paths.add(CONFIG_PATH);
    for (String path : paths) {
        try {
            if (new File(servletContext.getRealPath(path)).exists()) {
                finalPath = path;
                break;
            }
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
    }
    if (!Strings.isNullOrEmpty(finalPath)) {
        try {
            Gson gson = new Gson();
            String json = FileUtils.readFileToString(new File(servletContext.getRealPath(finalPath)));
            StudioConfiguration newStudioConfiguration = gson.fromJson(json, StudioConfiguration.class);
            studioConfiguration.setConfigurationFiles(newStudioConfiguration.getConfigurationFiles());
            studioConfiguration.setGoogleAnalyticsID(newStudioConfiguration.getGoogleAnalyticsID());
            studioConfiguration.setHelpURI(newStudioConfiguration.getHelpURI());
            studioConfiguration.setHelpMode(newStudioConfiguration.getHelpMode());
            studioConfiguration.setImages(newStudioConfiguration.getImages());
            studioConfiguration.setWorkbenchName(newStudioConfiguration.getWorkbenchName());
            studioConfiguration.setAdvancedMode(newStudioConfiguration.getAdvancedMode());
            studioConfiguration.setModules(newStudioConfiguration.getModules());
            studioConfiguration.setExtensionsFilter(newStudioConfiguration.getExtensionsFilter());
            studioConfiguration.setExtensionsPreferences(newStudioConfiguration.getExtensionsPreferences());
        } catch (IOException ex) {
            Logger.getLogger(StudioConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return studioConfiguration;
}


}