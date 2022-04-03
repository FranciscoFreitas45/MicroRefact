package com.metservice.kanban.webdriver;
 import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
public class BoardPage {

 private  WebDriver driver;

public BoardPage(WebDriver driver) {
    this.driver = driver;
}
public WorkItemPage clickAddFeatureButton(){
    driver.findElement(By.xpath("//div[@id='add-top-level-item-button']")).click();
    // tester.clickElementByXPath("//div[@id='add-top-level-item-button']");
    return new WorkItemPage(driver);
}


public WorkItemPage clickEditFeatureButton(String name){
    return clickEditButton("feature", name);
}


public BoardPage clickWallButton(){
    driver.findElement(By.xpath("//div[@id='wall']")).click();
    return this;
}


public WorkItemPage clickEditButton(String workItemTypeName,String workItemName){
    driver.findElement(By.xpath("//div[" + "@class='" + workItemTypeName + "' and .//span[@class='work-item-name' and .='" + workItemName + "']]" + "//img[@class='edit']")).click();
    return new WorkItemPage(driver);
}


public BoardPage openProject(String projectName){
    WebDriver driver = new HtmlUnitDriver(true);
    driver.navigate().to("http://localhost:8008/kanban");
    driver.findElement(By.linkText("Test project")).click();
    return new BoardPage(driver);
}


public BoardPage clickBacklogButton(){
    driver.findElement(By.xpath("//div[@id='backlog-button']")).click();
    return this;
}


public void assertFeatureNotPresent(String name){
    try {
        driver.findElement(By.xpath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]"));
        Assert.fail("feature exists: " + name);
    } catch (NoSuchElementException e) {
    // expected
    }
}


}