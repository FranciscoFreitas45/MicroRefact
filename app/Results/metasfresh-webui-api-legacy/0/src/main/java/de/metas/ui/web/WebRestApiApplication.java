package de.metas.ui.web;
 import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.adempiere.ad.migration.logger.IMigrationLogger;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.lang.IAutoCloseable;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.compiere.Adempiere;
import org.compiere.Adempiere.RunMode;
import org.compiere.model.ModelValidationEngine;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.common.logging.slf4j.Slf4jESLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.metas.JsonObjectMapperHolder;
import de.metas.MetasfreshBeanNameGenerator;
import de.metas.Profiles;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelection;
import de.metas.ui.web.session.WebRestApiContextProvider;
import de.metas.ui.web.window.model.DocumentInterfaceWrapperHelper;
import de.metas.util.Check;
import de.metas.util.Services;
@SpringBootApplication(scanBasePackages = { "de.metas", "org.adempiere" })
@EnableAsync
@Profile(Profiles.PROFILE_Webui)
public class WebRestApiApplication {

 private  String SYSCONFIG_PREFIX_WEBUI_SPRING_PROFILES_ACTIVE;

 public  String BEANNAME_WebuiTaskScheduler;

 private  String SYSTEM_PROPERTY_HEADLESS;

@Autowired
 private  ApplicationContext applicationContext;


@Bean(Adempiere.BEAN_NAME)
public Adempiere adempiere(WebRestApiContextProvider webuiContextProvider){
    Env.setContextProvider(webuiContextProvider);
    // because usually at the time the message is (lazy) parsed the user session context is no longer available.
    AdempiereException.enableCaptureLanguageOnConstructionTime();
    InterfaceWrapperHelper.registerHelper(new DocumentInterfaceWrapperHelper());
    Services.get(IMigrationLogger.class).addTableToIgnoreList(I_T_WEBUI_ViewSelection.Table_Name);
    final Adempiere adempiere = Env.getSingleAdempiereInstance(applicationContext);
    return adempiere;
}


public ArrayList<String> retrieveActiveProfilesFromSysConfig(){
    final ArrayList<String> activeProfiles = Services.get(ISysConfigBL.class).getValuesForPrefix(SYSCONFIG_PREFIX_WEBUI_SPRING_PROFILES_ACTIVE, 0, 0).entrySet().stream().map(Entry::getValue).collect(Collectors.toCollection(ArrayList::new));
    return activeProfiles;
}


@Bean
@Primary
public ObjectMapper jsonObjectMapper(){
    return JsonObjectMapperHolder.sharedJsonObjectMapper();
}


@Bean
public EmbeddedServletContainerCustomizer servletContainerCustomizer(){
    return servletContainer -> {
        final TomcatEmbeddedServletContainerFactory tomcatContainerFactory = (TomcatEmbeddedServletContainerFactory) servletContainer;
        tomcatContainerFactory.addConnectorCustomizers(connector -> {
            final AbstractHttp11Protocol<?> httpProtocol = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
            httpProtocol.setCompression("on");
            httpProtocol.setCompressionMinSize(256);
            final String mimeTypes = httpProtocol.getCompressibleMimeType();
            final String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE + ",application/javascript";
            httpProtocol.setCompressibleMimeType(mimeTypesWithJson);
        });
    };
}


public void main(String[] args){
    if (Check.isEmpty(System.getProperty("PropertyFile"), true)) {
        System.setProperty("PropertyFile", "./metasfresh.properties");
    }
    // Make sure slf4j is used (by default, in v2.4.4 log4j is used, see https://github.com/metasfresh/metasfresh-webui-api/issues/757)
    ESLoggerFactory.setDefaultFactory(new Slf4jESLoggerFactory());
    try (final IAutoCloseable c = ModelValidationEngine.postponeInit()) {
        Ini.setRunMode(RunMode.WEBUI);
        Adempiere.instance.startup(RunMode.WEBUI);
        final ArrayList<String> activeProfiles = retrieveActiveProfilesFromSysConfig();
        activeProfiles.add(Profiles.PROFILE_Webui);
        final String headless = System.getProperty(SYSTEM_PROPERTY_HEADLESS, Boolean.toString(true));
        new SpringApplicationBuilder(WebRestApiApplication.class).headless(// we need headless=false for initial connection setup popup (if any), usually this only applies on dev workstations.
        Boolean.parseBoolean(headless)).web(true).profiles(activeProfiles.toArray(new String[0])).beanNameGenerator(new MetasfreshBeanNameGenerator()).run(args);
    }
    // now init the model validation engine
    ModelValidationEngine.get();
}


@Bean(BEANNAME_WebuiTaskScheduler)
public TaskScheduler webuiTaskScheduler(){
    final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setThreadNamePrefix("webui-task-scheduler-");
    taskScheduler.setDaemon(true);
    taskScheduler.setPoolSize(10);
    return taskScheduler;
}


}