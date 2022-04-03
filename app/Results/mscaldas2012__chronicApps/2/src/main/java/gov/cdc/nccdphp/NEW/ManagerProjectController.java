package gov.cdc.nccdphp.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import gov.cdc.nccdphp.domain.Manager;
@RestController
@CrossOrigin
public class ManagerProjectController {

@Autowired
 private ManagerProjectService managerprojectservice;


}