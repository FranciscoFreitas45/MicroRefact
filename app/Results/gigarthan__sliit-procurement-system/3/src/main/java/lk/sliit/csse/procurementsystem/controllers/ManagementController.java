package lk.sliit.csse.procurementsystem.controllers;
 import lk.sliit.csse.procurementsystem.models.AccountingStaff;
import lk.sliit.csse.procurementsystem.models.ProcurementStaff;
import lk.sliit.csse.procurementsystem.models.SiteManager;
import lk.sliit.csse.procurementsystem.repositories.AccountingStaffRepository;
import lk.sliit.csse.procurementsystem.repositories.ProcurementStaffRepository;
import lk.sliit.csse.procurementsystem.repositories.SiteManagerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.inject.Named;
import java.util.List;
import lk.sliit.csse.procurementsystem.Interface.SiteManager;
import lk.sliit.csse.procurementsystem.Interface.SiteManagerRepository;
@Data
@Named
public class ManagementController {

 private  ProcurementStaff procurementStaff;

 private  AccountingStaff accountingStaff;

 private  SiteManager siteManager;

@Autowired
 private  ProcurementStaffRepository procurementStaffRepository;

@Autowired
 private  AccountingStaffRepository accountingStaffRepository;

@Autowired
 private  SiteManagerRepository siteManagerRepository;


public boolean addNewManager(){
    siteManager.setEnabled(true);
    siteManager.setPassword(new BCryptPasswordEncoder().encode("1234"));
    siteManagerRepository.save(siteManager);
    this.siteManager = new SiteManager();
    return true;
}


public void addNewAccountingStaff(){
    accountingStaff.setEnabled(true);
    accountingStaff.setPassword(new BCryptPasswordEncoder().encode("1234"));
    accountingStaffRepository.save(accountingStaff);
    this.accountingStaff = new AccountingStaff();
}


public List<SiteManager> getSiteManagers(){
    return siteManagerRepository.findAll();
}


public void deleteAccountingStaff(AccountingStaff as){
    accountingStaffRepository.delete(as);
}


public void deleteSiteManager(SiteManager sm){
    siteManagerRepository.delete(sm);
}


public void addNewProcurementStaff(){
    procurementStaff.setEnabled(true);
    procurementStaff.setPassword(new BCryptPasswordEncoder().encode("1234"));
    procurementStaffRepository.save(procurementStaff);
    this.procurementStaff = new ProcurementStaff();
}


public List<ProcurementStaff> getProcurementStaffs(){
    return procurementStaffRepository.findAll();
}


public void deleteProcurementStaff(ProcurementStaff ps){
    procurementStaffRepository.delete(ps);
}


public List<AccountingStaff> getAccountingStaffs(){
    return accountingStaffRepository.findAll();
}


}