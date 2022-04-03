package com.fosun.fc.projects.creepers.downloader;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
public class WebDriverPool {

 private  Logger logger;

 private  int DEFAULT_CAPACITY;

 private  int capacity;

 private  int STAT_RUNNING;

 private  int STAT_CLODED;

 private  AtomicInteger stat;

 private  WebDriver mDriver;

@SuppressWarnings("unused")
 private  boolean mAutoQuitDriver;

 private  String CONFIG_FILE;

 private  String DRIVER_FIREFOX;

 private  String DRIVER_CHROME;

 private  String DRIVER_PHANTOMJS;

 protected  Properties sConfig;

 protected  DesiredCapabilities sCaps;

 private  List<WebDriver> webDriverList;

 private  BlockingDeque<WebDriver> innerQueue;

public WebDriverPool(int capacity) {
    this.capacity = capacity;
}public WebDriverPool() {
    this(DEFAULT_CAPACITY);
}
public void checkRunning(){
    if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
        throw new IllegalStateException("Already closed!");
    }
}


public WebDriver get(){
    checkRunning();
    WebDriver poll = innerQueue.poll();
    if (poll != null) {
        return poll;
    }
    if (webDriverList.size() < capacity) {
        synchronized (webDriverList) {
            if (webDriverList.size() < capacity) {
                // add new WebDriver instance into pool
                try {
                    configure();
                    innerQueue.add(mDriver);
                    webDriverList.add(mDriver);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            // ChromeDriver e = new ChromeDriver();
            // WebDriver e = getWebDriver();
            // innerQueue.add(e);
            // webDriverList.add(e);
            }
        }
    }
    return innerQueue.take();
}


public void returnToPool(WebDriver webDriver){
    checkRunning();
    innerQueue.add(webDriver);
}


public boolean isUrl(String urlString){
    try {
        new URL(urlString);
        return true;
    } catch (MalformedURLException mue) {
        return false;
    }
}


public void configure(){
    // Read config file
    sConfig = new Properties();
    // sConfig.load(new FileReader(CONFIG_FILE));
    if (new File(CONFIG_FILE).exists()) {
        sConfig.load(new FileReader(CONFIG_FILE));
    }
    // Prepare capabilities
    sCaps = new DesiredCapabilities();
    sCaps.setJavascriptEnabled(true);
    sCaps.setCapability("takesScreenshot", false);
    String driver = sConfig.getProperty("driver", DRIVER_FIREFOX);
    // Fetch PhantomJS-specific configuration parameters
    if (driver.equals(DRIVER_PHANTOMJS)) {
        // "phantomjs_exec_path"
        if (sConfig.getProperty("phantomjs_exec_path") != null) {
            sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, sConfig.getProperty("phantomjs_exec_path"));
        } else {
            sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, BaseConstant.SeleniumWebDriverPath.PHANTOM_JS_PATH.getValue());
        // throw new IOException(
        // String.format(
        // "Property '%s' not set!",
        // PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY));
        }
        // "phantomjs_driver_path"
        if (sConfig.getProperty("phantomjs_driver_path") != null) {
            System.out.println("Test will use an external GhostDriver");
            sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY, sConfig.getProperty("phantomjs_driver_path"));
        } else {
            System.out.println("Test will use PhantomJS internal GhostDriver");
        }
    }
    // Disable "web-security", enable all possible "ssl-protocols" and
    // "ignore-ssl-errors" for PhantomJSDriver
    // sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new
    // String[] {
    // "--web-security=false",
    // "--ssl-protocol=any",
    // "--ignore-ssl-errors=true"
    // });
    ArrayList<String> cliArgsCap = new ArrayList<String>();
    cliArgsCap.add("--web-security=false");
    cliArgsCap.add("--ssl-protocol=any");
    cliArgsCap.add("--ignore-ssl-errors=true");
    sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
    // Control LogLevel for GhostDriver, via CLI arguments
    sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[] { "--logLevel=" + (sConfig.getProperty("phantomjs_driver_loglevel") != null ? sConfig.getProperty("phantomjs_driver_loglevel") : "INFO") });
    // String driver = sConfig.getProperty("driver", DRIVER_PHANTOMJS);
    // Start appropriate Driver
    if (isUrl(driver)) {
        sCaps.setBrowserName("phantomjs");
        mDriver = new RemoteWebDriver(new URL(driver), sCaps);
    } else if (driver.equals(DRIVER_FIREFOX)) {
        mDriver = new FirefoxDriver(sCaps);
    } else if (driver.equals(DRIVER_CHROME)) {
        mDriver = new ChromeDriver(sCaps);
    } else if (driver.equals(DRIVER_PHANTOMJS)) {
        mDriver = new PhantomJSDriver(sCaps);
    }
}


public void closeAll(){
    boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
    if (!b) {
        throw new IllegalStateException("Already closed!");
    }
    for (WebDriver webDriver : webDriverList) {
        logger.info("Quit webDriver" + webDriver);
        webDriver.quit();
        webDriver = null;
    }
}


}