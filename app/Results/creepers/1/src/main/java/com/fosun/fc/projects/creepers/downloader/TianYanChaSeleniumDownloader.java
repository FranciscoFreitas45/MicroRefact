package com.fosun.fc.projects.creepers.downloader;
 import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;
@Component("tianYanChaSeleniumDownloader")
public class TianYanChaSeleniumDownloader implements Closeable,Downloader{

 private  WebDriverPool webDriverPool;

 private  Logger logger;

 private  int sleepTime;

 private  int poolSize;

/**
 * 新建
 *
 * @param chromeDriverPath
 *            chromeDriverPath
 */
public TianYanChaSeleniumDownloader(String chromeDriverPath) {
    System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
}/**
 * Constructor without any filed. Construct PhantomJS browser
 *
 * @author bob.li.0718@gmail.com
 */
public TianYanChaSeleniumDownloader() {
    System.getProperties().setProperty("webdriver.chrome.driver", CommonMethodUtils.getChromeWebDriver());
    System.getProperties().setProperty("phantomjs.binary.path", CommonMethodUtils.getPhantomJSWebDriver());
    System.getProperties().setProperty("webdriver.firefox.bin", CommonMethodUtils.getFirefoxWebDriver());
}
@Override
public void setThread(int thread){
    this.poolSize = thread;
}


@SuppressWarnings("deprecation")
@Override
public Page download(Request request,Task task){
    checkInit();
    WebDriver webDriver;
    try {
        webDriver = webDriverPool.get();
    } catch (InterruptedException e) {
        logger.warn("interrupted", e);
        return null;
    }
    logger.info("downloading page " + request.getUrl());
    webDriver.get(request.getUrl());
    WebDriver.Options manage = webDriver.manage();
    Site site = task.getSite();
    if (site.getCookies() != null) {
        for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
            Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
            manage.addCookie(cookie);
        }
    }
    // 
    // WebElement inputWebElement =
    // webDriver.findElement(By.xpath("//*[@id=\"live-search\"]"));
    // inputWebElement.sendKeys("310104000610936");
    // inputWebElement.sendKeys(Keys.ENTER);
    try {
        Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    // *[@id=\"main_box\"]/div[3]/div/form/input
    /*
         * You can add mouse event or other processes
         * 
         * @author: bob.li.0718@gmail.com
         */
    WebElement webElement = webDriver.findElement(By.xpath("/html"));
    String content = webElement.getAttribute("outerHTML");
    Page page = new Page();
    page.setRawText(content);
    page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
    page.setUrl(new PlainText(request.getUrl()));
    page.setRequest(request);
    webDriverPool.returnToPool(webDriver);
    return page;
}


public void checkInit(){
    if (webDriverPool == null) {
        synchronized (this) {
            webDriverPool = new WebDriverPool(poolSize);
        }
    }
}


public TianYanChaSeleniumDownloader setSleepTime(int sleepTime){
    this.sleepTime = sleepTime;
    return this;
}


@Override
public void close(){
    webDriverPool.closeAll();
}


}