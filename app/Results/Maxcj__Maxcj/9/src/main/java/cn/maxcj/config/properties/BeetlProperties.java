package cn.maxcj.config.properties;
 import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;
@Configuration
@ConfigurationProperties(prefix = BeetlProperties.BEETLCONF_PREFIX)
public class BeetlProperties {

 public  String BEETLCONF_PREFIX;

 private  String delimiterStatementStart;

 private  String delimiterStatementEnd;

 private  String resourceTagroot;

 private  String resourceTagsuffix;

 private  String resourceAutoCheck;

@Value("${spring.mvc.view.prefix}")
 private  String prefix;


public void setDelimiterStatementStart(String delimiterStatementStart){
    this.delimiterStatementStart = delimiterStatementStart;
}


public String getPrefix(){
    return prefix;
}


public String getResourceAutoCheck(){
    return resourceAutoCheck;
}


public void setDelimiterStatementEnd(String delimiterStatementEnd){
    this.delimiterStatementEnd = delimiterStatementEnd;
}


public Properties getProperties(){
    Properties properties = new Properties();
    if (ToolUtil.isNotEmpty(delimiterStatementStart)) {
        if (delimiterStatementStart.startsWith("\\")) {
            delimiterStatementStart = delimiterStatementStart.substring(1);
        }
        properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
    }
    if (ToolUtil.isNotEmpty(delimiterStatementEnd)) {
        properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
    } else {
        properties.setProperty("DELIMITER_STATEMENT_END", "null");
    }
    if (ToolUtil.isNotEmpty(resourceTagroot)) {
        properties.setProperty("RESOURCE.tagRoot", resourceTagroot);
    }
    if (ToolUtil.isNotEmpty(resourceTagsuffix)) {
        properties.setProperty("RESOURCE.tagSuffix", resourceTagsuffix);
    }
    if (ToolUtil.isNotEmpty(resourceAutoCheck)) {
        properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck);
    }
    return properties;
}


public void setResourceTagroot(String resourceTagroot){
    this.resourceTagroot = resourceTagroot;
}


public String getResourceTagsuffix(){
    return resourceTagsuffix;
}


public String getDelimiterStatementEnd(){
    return delimiterStatementEnd;
}


public String getResourceTagroot(){
    return resourceTagroot;
}


public String getDelimiterStatementStart(){
    return delimiterStatementStart;
}


public void setResourceTagsuffix(String resourceTagsuffix){
    this.resourceTagsuffix = resourceTagsuffix;
}


public void setResourceAutoCheck(String resourceAutoCheck){
    this.resourceAutoCheck = resourceAutoCheck;
}


}