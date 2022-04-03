package com.metservice.kanban.jwebunit;
 import com.metservice.kanban.tests.util.TestUtils.createTestProject;
import org.apache.commons.io.FileUtils.deleteDirectory;
import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.rules.TemporaryFolder;
import com.metservice.kanban.jwebunit.util.UntilTrue;
import com.metservice.kanban.jwebunit.util.WaitFor;
public class BoardPage {

 protected  WebTester tester;

public BoardPage(WebTester tester) {
    this.tester = tester;
}
public void assertFeatureIsPresent(String name){
    tester.assertElementPresentByXPath("//td[.='" + name + " ']");
}


public void assertItemNameIsIndicatedMustHave(int i){
    String itemClass = tester.getElementAttributeByXPath("//td[@id='item-name-" + i + "']", "class");
    assertTrue("Item should contain itemMustHave style", itemClass.contains("itemMustHave"));
    assertFalse("Item mustn't contain itemNiceToHave style", itemClass.contains("itemNiceToHave"));
}


public WorkItemPage clickEditStoryButton(String name){
    return clickEditButton("story", name);
}


public void assertItemNameIsIndicatedNiceToHave(int i){
    String itemClass = tester.getElementAttributeByXPath("//td[@id='item-name-" + i + "']", "class");
    assertTrue("Item should contain itemNiceToHave style", itemClass.contains("itemNiceToHave"));
    assertFalse("Item mustn't contain itemMustHave style", itemClass.contains("itemMustHave"));
}


public ChartPage clickFeatureCycleTimeChartButton(){
    tester.clickElementByXPath("//a[@id='cycle-time-chart-1-button']");
    return new ChartPage(tester);
}


public void assertProjectListIsSorted(){
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[1][contains(text(), \"123\")]");
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[2][contains(text(), \"A1z\")]");
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[3][contains(text(), \"ABC\")]");
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[4][contains(text(), \"acb\")]");
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[5][contains(text(), \"XYZ\")]");
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[6][contains(text(), \"xzy\")]");
}


public void assertFeatureNotPresent(String name){
    tester.assertElementNotPresentByXPath("//td[.='" + name + " ']");
}


public WorkItemPage clickAddFeatureButton(){
    tester.clickElementByXPath("//a[@id='add-top-level-item-button']");
    return new WorkItemPage(tester);
}


public void assertProjectIsPresent(String name){
    tester.assertElementPresentByXPath("//select[@id=\"projectPicker\"]/option[1][contains(text(), \"" + name + "\")]");
}


public BoardPage enterQuickName(String nameValue){
    tester.clickElementByXPath("//input[@id='quick-editor-name']");
    tester.setTextField("name", nameValue);
    return this;
}


public BoardPage createBoardPage(String projectName){
    WebTester tester = new WebTester();
    tester.beginAt("http://localhost:8008/kanban");
    tester.clickLinkWithExactText(projectName);
    return new BoardPage(tester);
}


public BoardPage clickCompleteButton(){
    tester.clickElementByXPath("//a[@id='complete']");
    return this;
}


public JournalPage clickJournalButton(){
    tester.clickElementByXPath("//a[@id='journal']");
    return new JournalPage(tester);
}


public WorkItemPage clickAddStoryButton(String name){
    // If this fails we couldn't find the <div>.
    tester.assertElementPresentByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]");
    // If this fails, we found the <div> but couldn't find the <img> inside it.
    tester.clickElementByXPath("//div[@class='feature' and .//span[@class='work-item-name' and .='" + name + "']]//img[@class='add']");
    return new WorkItemPage(tester);
}


public String clickDownloadFeaturesButton(){
    tester.clickElementByXPath("//div[@id='feature-download-button']");
    return tester.getPageSource();
}


public AdminPage clickAdminButton(){
    // tester.clickElementByXPath("//a[@id='admin']");
    tester.clickLink("admin");
    // tester.assertTitleEquals("Kanban: admin");
    return new AdminPage(tester);
}


public WorkItemPage clickEditButton(String workItemTypeName,String workItemName){
    tester.clickElementByXPath("//td[.='" + workItemName + " ']/../td[2]/a");
    return new WorkItemPage(tester);
}


public void assertJournalHeaderIsPresent(String headerText){
    final String journalHeaderId = "journal-header-1";
    tester.assertElementPresent(journalHeaderId);
    tester.assertTextInElement(journalHeaderId, headerText);
}


public ChartPage clickCumulativeFlowChartButton(){
    tester.clickElementByXPath("//a[@id='cumulative-flow-chart-1-button']");
    return new ChartPage(tester);
}


public void cleanProject(TemporaryFolder kanbanHome){
    File root = kanbanHome.getRoot();
    deleteDirectory(root);
    root.mkdir();
}


public BoardPage setCurrentUser(String nameValue){
    tester.clickElementByXPath("//input[@id='userField']");
    tester.setTextField("userField", nameValue);
    return this;
}


public BoardPage clickAdvance(String name){
    String xPath = "//td[.='" + name + " ']/../td[8]/a/img";
    tester.assertElementPresentByXPath(xPath);
    tester.clickElementByXPath(xPath);
    return this;
}


public ChartPage clickBurnUpChartButton(){
    tester.clickElementByXPath("//a[@id='burn-up-chart-button']");
    return new ChartPage(tester);
}


public void assertJournalTextIsPresent(String headerText,String journaltext){
    final String journalTextId = "journal-text-1";
    int timeout = 5000;
    int timeBetweenChecks = 100;
    WaitFor.me(new UntilTrue() {

        @Override
        public boolean condition() {
            tester.assertElementPresent(journalTextId);
            return true;
        }
    }, timeout, timeBetweenChecks);
    assertJournalHeaderIsPresent(headerText);
    tester.assertTextInElement(journalTextId, journaltext);
}


@Override
public boolean condition(){
    tester.assertElementPresent(journalTextId);
    return true;
}


public WorkItemPage clickEditFeatureButton(String name){
    return clickEditButton("feature", name);
}


public WallPage clickWallButton(){
    // tester.clickElementByXPath("//a[@id='wall']");
    tester.clickLink("wall");
    tester.assertTitleEquals("Kanban: wall");
    return new WallPage(tester);
}


public BoardPage openProject(TemporaryFolder kanbanHome,String projectName,String sourceResourcePath){
    File root = kanbanHome.getRoot();
    cleanProject(kanbanHome);
    createTestProject(root, projectName, sourceResourcePath);
    return createBoardPage(projectName);
}


public PETPage clickPETButton(){
    tester.clickElementByXPath("//a[@id='pet']");
    return new PETPage(tester);
}


public void assertCompleteItemWidthIsCorrect(int item,int phase,int size){
    String itemStyle = tester.getElementAttributeByXPath(".//*[@id='work-item-" + item + "']/div[" + phase + "]", "style");
    assertTrue("Verify phase width in px", itemStyle.toString().contains("width:" + size + "px"));
}


public BoardPage clickBacklogButton(){
    tester.clickElementByXPath("//a[@id='backlog-button']");
    return new BoardPage(tester);
}


public void assertProjectNotPresent(String name){
    tester.assertElementNotPresentByXPath("//select[@id=\"projectPicker\"]/option[1][contains(text(), \"" + name + "\")]");
}


}