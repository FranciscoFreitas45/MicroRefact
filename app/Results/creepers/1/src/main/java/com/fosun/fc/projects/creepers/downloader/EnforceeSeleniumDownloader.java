package com.fosun.fc.projects.creepers.downloader;
 import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.CookieUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;
@Component("enforceeSeleniumDownloader")
public class EnforceeSeleniumDownloader implements Closeable,Downloader{

 private  WebDriverPool webDriverPool;

 private  Logger logger;

@SuppressWarnings("unused")
 private  int sleepTime;

 private  int poolSize;

 private  String imageXPath;

@SuppressWarnings("unused")
 private  String cookieFilePath;

/**
 * 新建
 *
 * @param chromeDriverPath
 *            chromeDriverPath
 */
public EnforceeSeleniumDownloader(String chromeDriverPath) {
    System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
}/**
 * Constructor without any filed. Construct PhantomJS browser
 */
public EnforceeSeleniumDownloader() {
    System.getProperties().setProperty("webdriver.chrome.driver", CommonMethodUtils.getChromeWebDriver());
    System.getProperties().setProperty("phantomjs.binary.path", CommonMethodUtils.getPhantomJSWebDriver());
    System.getProperties().setProperty("webdriver.firefox.bin", CommonMethodUtils.getFirefoxWebDriver());
}
@Override
public void setThread(int thread){
    this.poolSize = thread;
}


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
    if (request.getUrl().startsWith("http://zhixing.court.gov.cn/search/newdetail")) {
        return detailPage(webDriver, request, task);
    } else {
        return mainPage(webDriver, request, task);
    }
}


public void checkInit(){
    if (webDriverPool == null) {
        synchronized (this) {
            webDriverPool = new WebDriverPool(poolSize);
        }
    }
}


@SuppressWarnings("deprecation")
public Page mainPage(WebDriver webDriver,Request request,Task task){
    WebDriver.Options manage = webDriver.manage();
    logger.info("downloading page " + request.getUrl());
    Site site = task.getSite();
    if (site.getCookies() != null) {
        for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
            Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
            manage.addCookie(cookie);
        }
    }
    webDriver.get(request.getUrl());
    // try {
    // Thread.sleep(sleepTime);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    WebDriverWait wait = new WebDriverWait(webDriver, 600);
    wait.until(new ExpectedCondition<Boolean>() {

        public Boolean apply(WebDriver webDriver) {
            return webDriver.findElement(By.xpath(imageXPath)).isDisplayed();
        }
    });
    CookieUtils.addCookieToFile(webDriver);
    WebElement imgElement = webDriver.findElement(By.xpath(imageXPath));
    // 获得webElement的位置和大小。
    Point location = imgElement.getLocation();
    Dimension size = imgElement.getSize();
    // 创建全屏截图。
    BufferedImage originalImage = null;
    try {
        originalImage = ImageIO.read(new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
    } catch (WebDriverException | IOException e) {
        logger.info("图片截取失败！", e);
    }
    // 截取webElement所在位置的子图。
    BufferedImage croppedImage = originalImage.getSubimage(location.getX(), location.getY(), size.getWidth(), size.getHeight());
    String imagePath = "/image/" + UUID.randomUUID() + ".jpg";
    try {
        File filePath = new File("/image");
        if (filePath.exists()) {
            logger.info("多级目录已经存在不需要创建！！");
        } else {
            filePath.mkdirs();
        }
        ImageIO.write(croppedImage, "JPEG", new File(imagePath));
    } catch (IOException e) {
        logger.info("验证码图片输出失败!", e);
    }
    String imageValue = CommonMethodUtils.imageAnalytical(imagePath, BaseConstant.ImageAnalyticalType.FILE_PATH.getValue(), "http://zhixing.court.gov.cn/search/security/jcaptcha.jpg");
    logger.info(imageValue);
    String resultValue = CommonMethodUtils.getNodeValue(CommonMethodUtils.getChildElement(CommonMethodUtils.getChildElement(CommonMethodUtils.getRootElement(imageValue), "ResultList"), "Item"), "Result");
    logger.info(resultValue);
    // *[@id=\"pname\"]
    WebElement pnameElement = webDriver.findElement(By.xpath("//*[@id=\"pname\"]"));
    // pnameElement.clear();
    pnameElement.sendKeys("金国平");
    // *[@id="j_captcha"]
    WebElement jCaptchaElement = webDriver.findElement(By.xpath("//*[@id=\"j_captcha\"]"));
    // jCaptchaElement.clear();
    jCaptchaElement.sendKeys(resultValue);
    webDriver.findElement(By.xpath("//*[@id=\"button\"]")).submit();
    webDriver.switchTo().frame("contentFrame");
    // webDriver.switchTo().defaultContent();
    // String strMainHandler = webDriver.getWindowHandle();
    // webDriver.switchTo().frame(strMainHandler);
    wait = new WebDriverWait(webDriver, 600);
    wait.until(new ExpectedCondition<Boolean>() {

        public Boolean apply(WebDriver webDriver) {
            return webDriver.findElement(By.xpath("//*[@id=\"ResultlistBlock\"]")).isDisplayed();
        }
    });
    // http://zhixing.court.gov.cn/search/security/jcaptcha.jpg?40
    // http://zhixing.court.gov.cn/search/security/jcaptcha.jpg?61
    // http://zhixing.court.gov.cn/search/newdetail?id=96889359&j_captcha=38135
    // http://zhixing.court.gov.cn/search/security/jcaptcha.jpg?75
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


public Boolean apply(WebDriver webDriver){
    return webDriver.findElement(By.xpath(imageXPath)).isDisplayed();
}


public EnforceeSeleniumDownloader setImageXPath(String imageXPath){
    this.imageXPath = imageXPath;
    return this;
}


public EnforceeSeleniumDownloader setCookieFilePath(String cookieFilePath){
    this.cookieFilePath = cookieFilePath;
    return this;
}


@SuppressWarnings({ "resource", "unused", "deprecation" })
public Page detailPage(WebDriver webDriver,Request request,Task task){
    WebDriver.Options manage = webDriver.manage();
    logger.info("downloading page " + request.getUrl());
    try {
        File file = new File("/cookie/broswer.data");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(line, ";");
            while (str.hasMoreTokens()) {
                String name = str.nextToken();
                String value = str.nextToken();
                String domain = str.nextToken();
                String path = str.nextToken();
                Date expiry = null;
                String dt;
                if (!(dt = str.nextToken()).equals(null)) {
                    // expiry=new Date(dt);
                    System.out.println("!(dt = str.nextToken()).equals(null)");
                }
                boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                manage.addCookie(ck);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    webDriver.get(request.getUrl());
    // try {
    // Thread.sleep(sleepTime);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    WebDriverWait wait = new WebDriverWait(webDriver, 600);
    wait.until(new ExpectedCondition<Boolean>() {

        public Boolean apply(WebDriver webDriver) {
            return webDriver.findElement(By.xpath(imageXPath)).isDisplayed();
        }
    });
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


public EnforceeSeleniumDownloader setSleepTime(int sleepTime){
    this.sleepTime = sleepTime;
    return this;
}


@Override
public void close(){
    webDriverPool.closeAll();
}


}