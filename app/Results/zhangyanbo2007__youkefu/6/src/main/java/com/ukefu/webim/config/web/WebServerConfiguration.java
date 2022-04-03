package com.ukefu.webim.config.web;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ukefu.util.UKTools;
@Configuration
public class WebServerConfiguration {

 private  Integer maxthread;

 private  Integer maxconnections;

@Value("${web.upload-path}")
 private  String path;


@Bean
public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory(){
    TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
    tomcatFactory.addConnectorCustomizers(new UKeFuTomcatConnectorCustomizer(maxthread, maxconnections));
    File sslFile = new File(path, "ssl/https.properties");
    if (sslFile.exists()) {
        Properties sslProperties = new Properties();
        FileInputStream in = new FileInputStream(sslFile);
        sslProperties.load(in);
        in.close();
        if (!StringUtils.isBlank(sslProperties.getProperty("key-store")) && !StringUtils.isBlank(sslProperties.getProperty("key-store-password"))) {
            Ssl ssl = new Ssl();
            ssl.setKeyStore(new File(path, "ssl/" + sslProperties.getProperty("key-store")).getAbsolutePath());
            ssl.setKeyStorePassword(UKTools.decryption(sslProperties.getProperty("key-store-password")));
            tomcatFactory.setSsl(ssl);
        }
    }
    return tomcatFactory;
}


}