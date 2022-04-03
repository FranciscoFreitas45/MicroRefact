package org.gliderwiki.rest.client;
 import org.gliderwiki.rest.bean.Employee;
import org.gliderwiki.rest.bean.EmployeeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.WireFeedOutput;
public class EmployeeClient {


public void listXML(RestTemplate rest){
    System.out.println("@@@ rest : " + rest.toString());
    System.out.println("@@@ MediaType.APPLICATION_XML		 		: " + MediaType.APPLICATION_XML);
    System.out.println("@@@ MediaType.APPLICATION_ATOM_XML   		: " + MediaType.APPLICATION_ATOM_XML);
    System.out.println("@@@ MediaType.APPLICATION_FORM_URLENCODED   : " + MediaType.APPLICATION_FORM_URLENCODED);
    System.out.println("@@@ MediaType.APPLICATION_JSON 				: " + MediaType.APPLICATION_JSON);
    System.out.println("@@@ MediaType.APPLICATION_OCTET_STREAM 		: " + MediaType.APPLICATION_OCTET_STREAM);
    System.out.println("@@@ MediaType.APPLICATION_XHTML_XML 		: " + MediaType.APPLICATION_XHTML_XML);
    System.out.println("@@@ MediaType.MULTIPART_FORM_DATA 			: " + MediaType.MULTIPART_FORM_DATA);
    System.out.println("@@@ MediaType.IMAGE_GIF 					: " + MediaType.IMAGE_GIF);
    System.out.println("@@@ MediaType.IMAGE_JPEG 					: " + MediaType.IMAGE_JPEG);
    System.out.println("@@@ MediaType.IMAGE_PNG 					: " + MediaType.IMAGE_PNG);
    System.out.println("@@@ MediaType.TEXT_HTML 					: " + MediaType.TEXT_HTML);
    System.out.println("@@@ MediaType.TEXT_PLAIN 					: " + MediaType.TEXT_PLAIN);
    System.out.println("@@@ MediaType.TEXT_XML 						: " + MediaType.TEXT_XML);
    HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_XML);
    ResponseEntity<EmployeeList> response = rest.exchange("http://localhost/service/emps", HttpMethod.GET, entity, EmployeeList.class);
    System.out.println("@@@ response.toString() : " + response.toString());
    System.out.println("@@@ response.getBody() : " + response.getBody().getClass());
    System.out.println("@@@ response.getBody() : " + response.getBody().getEmployees());
    EmployeeList employees = response.getBody();
    for (Employee e : employees.getEmployees()) {
        System.out.println("# " + e.getId() + ": " + e.getName());
    }
}


public void postEmployee(RestTemplate rest){
    Employee newEmp = new Employee(99, "guest", "guest@ibm.com");
    HttpEntity<Employee> entity = new HttpEntity<Employee>(newEmp);
    ResponseEntity<Employee> response = rest.postForEntity("http://localhost/service/emp", entity, Employee.class);
    Employee e = response.getBody();
    System.out.println("New employee: " + e.getId() + ", " + e.getName());
}


public void listAtom(RestTemplate rest){
    HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_ATOM_XML);
    ResponseEntity<Feed> response = rest.exchange("http://localhost/service/emps", HttpMethod.GET, entity, Feed.class);
    WireFeed atomFeed = response.getBody();
    WireFeedOutput output = new WireFeedOutput();
    try {
        System.out.println(output.outputString(atomFeed));
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public RestTemplate getTemplate(){
    ApplicationContext ctx = new FileSystemXmlApplicationContext("WebContent/WEB-INF/spring/rest/rest-servlet.xml", "WebContent/WEB-INF/spring/rest/rest-context.xml");
    RestTemplate template = (RestTemplate) ctx.getBean("restTemplate");
    return template;
}


public void listJson(RestTemplate rest){
    HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON);
    ResponseEntity<EmployeeList> response = rest.exchange("http://localhost/service/emps", HttpMethod.GET, entity, EmployeeList.class);
    EmployeeList employees = response.getBody();
    for (Employee e : employees.getEmployees()) {
        System.out.println(e.getId() + ": " + e.getName());
    }
}


public void updateEmployee(RestTemplate rest){
    Employee newEmp = new Employee(99, "guest99", "guest99@ibm.com");
    HttpEntity<Employee> entity = new HttpEntity<Employee>(newEmp);
    rest.put("http://localhost/service/emp/{id}", entity, "99");
}


public HttpEntity<String> prepareGet(MediaType type){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(type);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    return entity;
}


public void main(String[] args){
    // restTemplate
    RestTemplate restTemplate = getTemplate();
    listXML(restTemplate);
    postEmployee(restTemplate);
    updateEmployee(restTemplate);
    listAtom(restTemplate);
    removeEmployee(restTemplate);
    listJson(restTemplate);
}


public void removeEmployee(RestTemplate rest){
    rest.delete("http://localhost/service/emp/{id}", "99");
}


}