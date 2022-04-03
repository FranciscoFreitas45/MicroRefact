package gov.cdc.nccdphp.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import gov.cdc.nccdphp.domain.Division;
@RestController
@CrossOrigin
public class DivisionProjectController {

@Autowired
 private DivisionProjectService divisionprojectservice;


}