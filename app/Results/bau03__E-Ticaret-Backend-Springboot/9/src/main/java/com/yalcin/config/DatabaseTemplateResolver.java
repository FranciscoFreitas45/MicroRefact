package com.yalcin.config;
 import com.yalcin.entity.Template;
import com.yalcin.repository.TemplateRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;
import java.util.Collections;
import java.util.Map;
import org.slf4j.LoggerFactory.getLogger;
@Component
public class DatabaseTemplateResolver extends StringTemplateResolver{

 private  Logger logger;

@Autowired
 private TemplateRepository templateRepository;

public DatabaseTemplateResolver() {
    this.setResolvablePatterns(Collections.singleton("db-*"));
    this.setCacheTTLMs(5 * 60 * 1000L);
    this.setCacheable(true);
}
@Override
public ITemplateResource computeTemplateResource(IEngineConfiguration configuration,String ownerTemplate,String templateName,Map<String,Object> templateResolutionAttributes){
    logger.info("Loading template named {} from DB", templateName);
    Template template = templateRepository.findByTemplateName(templateName);
    if (template == null) {
        logger.info("Template {} could not be loaded", templateName);
        return null;
    }
    return super.computeTemplateResource(configuration, ownerTemplate, template.getContent(), templateResolutionAttributes);
}


}