package com.metservice.kanban.jwebunit;
 import com.metservice.kanban.tests.util.TestUtils.createTestProject;
import org.apache.commons.io.FileUtils.deleteDirectory;
import org.junit.Assert.assertFalse;
import org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.rules.TemporaryFolder;
public class AdminPage {

 protected  WebTester tester;

public AdminPage(WebTester tester) {
    this.tester = tester;
}
public void cleanProject(TemporaryFolder kanbanHome){
    File root = kanbanHome.getRoot();
    deleteDirectory(root);
    root.mkdir();
}


public ProjectPropertiesPage clickEditProject(){
    tester.clickLinkWithExactText("Edit Project");
    return new ProjectPropertiesPage(tester);
}


public AdminPage createBoardPage(String projectName){
    WebTester tester = new WebTester();
    tester.beginAt("http://localhost:8008/kanban");
    tester.clickLinkWithExactText(projectName);
    return new AdminPage(tester);
}


public AdminPage openProject(TemporaryFolder kanbanHome,String projectName,String sourceResourcePath){
    File root = kanbanHome.getRoot();
    cleanProject(kanbanHome);
    createTestProject(root, projectName, sourceResourcePath);
    return createBoardPage(projectName);
}


}