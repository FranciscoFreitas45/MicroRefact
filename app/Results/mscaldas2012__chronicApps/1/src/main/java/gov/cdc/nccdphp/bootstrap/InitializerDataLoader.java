package gov.cdc.nccdphp.bootstrap;
 import gov.cdc.nccdphp.domain.Division;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletConfigAware;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.List;
@Service
public class InitializerDataLoader {

@Autowired
 private  DivisionRepository divisionRepository;

@Autowired
 private ServletContext servletContext;


public void loadDivisions(){
    System.out.println("----------------- Initializing Divisions ------------------");
    servletContext.setAttribute("allDivisions", divisionRepository.findAll());
}


}