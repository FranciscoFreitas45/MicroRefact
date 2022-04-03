package lk.sliit.csse.procurementsystem;
 import lk.sliit.csse.procurementsystem.models.Employee;
import lk.sliit.csse.procurementsystem.models.SiteManager;
import lk.sliit.csse.procurementsystem.repositories.SiteManagerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
@Data
@Named
public class HomeController {

 private  SiteManager siteManager;

@Autowired
 private  SiteManagerRepository siteManagerRepository;


}