package com.metservice.kanban.jwebunit;
 import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;
import net.sourceforge.jwebunit.junit.WebTester;
public class WallPage {

 final  WebTester tester;

public WallPage(WebTester tester) {
    this.tester = tester;
}
public void assertFeatureIsPresent(String name){
    tester.assertElementPresentByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]");
}


public WorkItemPage clickEditStoryButton(String name){
    return clickEditButton("story", name);
}


public WorkItemPage clickEditButton(String workItemTypeName,String workItemName){
    // if this fails, the drop down menu button is not present
    tester.clickElementByXPath("//div[@class='story' and .//span[@class='work-item-name' and .='story']]//button");
    // if this fails, the edit menu item is not present
    tester.assertElementPresentByXPath("//div[@class='story' and .//span[@class='work-item-name' and .='story']]//div//div//a[@class='edit']");
    // if this fails, we were unable to click the element
    tester.clickElementByXPath("//div[" + "@class='" + workItemTypeName + "' and .//span[@class='work-item-name' and .='" + workItemName + "']]" + "//div//div//a[@class='edit']");
    return new WorkItemPage(tester);
}


public void assertWipBroken(String columnName){
    String styleClass = tester.getElementByXPath("//th[span/text()='" + columnName + "']").getAttribute("class");
    assertTrue(styleClass.contains("brokenWIPLimit"));
}


public ChartPage clickFeatureCycleTimeChartButton(){
    tester.clickElementByXPath("//div[@id='cycle-time-chart-1-button']");
    return new ChartPage(tester);
}


public void assertFeatureNotPresent(String name){
    tester.assertElementNotPresentByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]");
}


public WallPage clickAdvance(String name){
    // If this fails we couldn't find the <div>.
    tester.assertElementPresentByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]");
    // If this fails, we found the <div> but couldn't find the <img> inside it.
    tester.clickElementByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]//img[@class='advance']");
    return this;
}


public WorkItemPage clickAddFeatureButton(){
    tester.clickElementByXPath("//div[@id='add-top-level-item-button']");
    return new WorkItemPage(tester);
}


public ChartPage clickBurnUpChartButton(){
    tester.clickElementByXPath("//div[@id='burn-up-chart-button']");
    return new ChartPage(tester);
}


public WorkItemPage clickEditFeatureButton(String name){
    return clickEditButton("feature", name);
}


public String getNotesForItem(int i){
    return tester.getElementByXPath("//div[@id='work-item-" + i + "']").getAttribute("title");
}


public void assertWipNotBroken(String columnName){
    String styleClass = tester.getElementByXPath("//th[span/text()='" + columnName + "']").getAttribute("class");
    assertFalse(styleClass.contains("brokenWIPLimit"));
}


public WallPage clickWallButton(){
    tester.clickElementByXPath("//a[@id='wall']");
    return this;
}


public WallPage openProject(String projectName){
    WebTester tester = new WebTester();
    tester.beginAt("http://localhost:8008/kanban");
    tester.clickLinkWithExactText("Test project");
    return new WallPage(tester);
}


public WorkItemPage clickAddStoryButton(String name){
    // If this fails we couldn't find the <div>.
    tester.assertElementPresentByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]");
    // If this fails, we found the <div> but couldn't find the <img> inside it.
    tester.clickElementByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]//div//div//a[@class='add']");
    return new WorkItemPage(tester);
}


public BoardPage clickBacklogButton(){
    tester.clickElementByXPath("//a[@id='backlog-button']");
    return new BoardPage(tester);
}


}