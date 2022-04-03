package gov.cdc.nccdphp.bootstrap;
 import gov.cdc.nccdphp.domain.Division;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
public class DivisionLoader implements ApplicationListener<ContextRefreshedEvent>{

@Autowired
 private  DivisionRepository divisionRepository;

 private  Logger log;


@Override
public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
    Division dach = new Division();
    dach.setAbbreviation("DACH");
    dach.setName("Division of Adult and Community Health");
    divisionRepository.save(dach);
    log.info("Saved " + dach.getAbbreviation() + " - " + dach.getId());
    Division dash = new Division();
    dash.setAbbreviation("DASH");
    dash.setName("Division of Adolescent and School Health");
    divisionRepository.save(dash);
    log.info("Saved " + dash.getAbbreviation() + " - " + dash.getId());
    Division dch = new Division();
    dch.setAbbreviation("DCH");
    dch.setName("Division of Community Health");
    divisionRepository.save(dch);
    log.info("Saved " + dch.getAbbreviation() + " - " + dch.getId());
    Division dcpc = new Division();
    dcpc.setAbbreviation("DCPC");
    dcpc.setName("Division of Cancer Prevention and Control");
    divisionRepository.save(dcpc);
    log.info("Saved " + dcpc.getAbbreviation() + " - " + dcpc.getId());
    Division ddt = new Division();
    ddt.setAbbreviation("DDT");
    ddt.setName("Division of Diabetes Translation");
    divisionRepository.save(ddt);
    log.info("Saved " + ddt.getAbbreviation() + " - " + ddt.getId());
    Division osh = new Division();
    osh.setAbbreviation("OSH");
    osh.setName("Office of Smoking and Health");
    divisionRepository.save(osh);
    log.info("Saved " + osh.getAbbreviation() + " - " + osh.getId());
    Division dhdsp = new Division();
    dhdsp.setAbbreviation("DHDSP");
    dhdsp.setName("Division of health Diseases and Stroke Prevention");
    divisionRepository.save(dhdsp);
    log.info("Saved " + dhdsp.getAbbreviation() + " - " + dhdsp.getId());
    Division dnpao = new Division();
    dnpao.setAbbreviation("DNPAO");
    dnpao.setName("Division of Nutrition, Physical Activity, Overweight and Obesity");
    divisionRepository.save(dnpao);
    log.info("Saved " + dnpao.getAbbreviation() + " - " + dnpao.getId());
    Division doh = new Division();
    doh.setAbbreviation("DOH");
    doh.setName("Division of Oral Health");
    divisionRepository.save(doh);
    log.info("Saved " + doh.getAbbreviation() + " - " + doh.getId());
    Division dph = new Division();
    dph.setAbbreviation("DPH");
    dph.setName("Division of Population Health");
    divisionRepository.save(dph);
    log.info("Saved " + dph.getAbbreviation() + " - " + dph.getId());
    Division drh = new Division();
    drh.setAbbreviation("DRH");
    drh.setName("Division of Reproductive Health");
    divisionRepository.save(drh);
    log.info("Saved " + drh.getAbbreviation() + " - " + drh.getId());
    Division od = new Division();
    od.setAbbreviation("OD");
    od.setName("Office of the Director");
    divisionRepository.save(od);
    log.info("Saved " + od.getAbbreviation() + " - " + od.getId());
    Division ncezid = new Division();
    ncezid.setAbbreviation("NCEZID");
    ncezid.setName("National Center of Emerging and Zoonotic Infectious Diseases ");
    divisionRepository.save(ncezid);
    log.info("Saved " + ncezid.getAbbreviation() + " - " + ncezid.getId());
    Division nchstp = new Division();
    nchstp.setAbbreviation("NCHSTP");
    nchstp.setName("NCHSTP");
    divisionRepository.save(nchstp);
    log.info("Saved " + nchstp.getAbbreviation() + " - " + nchstp.getId());
}


}