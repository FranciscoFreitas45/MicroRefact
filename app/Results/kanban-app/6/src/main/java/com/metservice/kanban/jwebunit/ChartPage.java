package com.metservice.kanban.jwebunit;
 import net.sourceforge.jwebunit.junit.WebTester;
public class ChartPage extends BoardPage{

public ChartPage(WebTester tester) {
    super(tester);
}
public void assertStartDateEquals(String startDate){
    tester.assertTextFieldEquals("startDate", startDate);
}


public void assertImageIsValidPng(String src){
    tester.assertImageValid(src, null);
}


public void dumpPageSourceToConsole(){
    System.out.println(tester.getPageSource());
}


}