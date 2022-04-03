package com.metservice.kanban.jwebunit;
 import net.sourceforge.jwebunit.junit.WebTester;
public class WorkItemPage {

 private  WebTester tester;

public WorkItemPage(WebTester tester) {
    this.tester = tester;
}
public BoardPage clickSaveButton(){
    tester.clickButton("save-button");
    return new BoardPage(tester);
}


public WorkItemPage untickExcludeBox(){
    tester.getTestingEngine().setWorkingForm("edit", 0);
    tester.uncheckCheckbox("excluded");
    return this;
}


public BoardPage clickDeleteButtonAndConfirm(){
    tester.setExpectedJavaScriptConfirm("Permanently delete this work item?", true);
    tester.clickButton("delete-button");
    return new BoardPage(tester);
}


public void dumpPageSourceToConsole(){
    System.out.println(tester.getPageSource());
}


public WorkItemPage enterNotes(String name){
    tester.setTextField("notes", name);
    return this;
}


public WorkItemPage assertExcludeBoxIs(Boolean excluded){
    tester.getTestingEngine().setWorkingForm("edit", 0);
    if (excluded) {
        tester.assertCheckboxSelected("excluded");
    } else {
        tester.assertCheckboxNotSelected("excluded");
    }
    return this;
}


public WorkItemPage assertParentIs(String featureName){
    tester.assertSelectedOptionEquals("parentId", featureName);
    return this;
}


public WorkItemPage enterAverageCase(String size){
    tester.setTextField("averageCaseEstimate", size);
    return this;
}


public WorkItemPage enterWorstCase(String size){
    tester.setTextField("worstCaseEstimate", size);
    return this;
}


public WorkItemPage enterName(String name){
    tester.setTextField("name", name);
    return this;
}


public WorkItemPage setParent(String name){
    tester.selectOption("parentId", name);
    return this;
}


public WorkItemPage tickExcludeBox(){
    tester.getTestingEngine().setWorkingForm("edit", 0);
    tester.checkCheckbox("excluded");
    return this;
}


public PrintPage clickSaveAndPrintButton(){
    tester.clickButton("save-and-print-button");
    return new PrintPage(tester);
}


}