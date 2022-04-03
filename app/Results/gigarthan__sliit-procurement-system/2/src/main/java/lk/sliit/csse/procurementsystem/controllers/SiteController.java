package lk.sliit.csse.procurementsystem.controllers;
 import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lk.sliit.csse.procurementsystem.models.BlacklistedSupplier;
import lk.sliit.csse.procurementsystem.models.Site;
import lk.sliit.csse.procurementsystem.models.SiteItem;
import lk.sliit.csse.procurementsystem.models.SiteManager;
import lk.sliit.csse.procurementsystem.models.Supplier;
import lk.sliit.csse.procurementsystem.repositories.BlacklistedSupplierRepository;
import lk.sliit.csse.procurementsystem.repositories.SiteItemRepository;
import lk.sliit.csse.procurementsystem.repositories.SiteManagerRepository;
import lk.sliit.csse.procurementsystem.repositories.SiteRepository;
import lk.sliit.csse.procurementsystem.repositories.SupplierRepository;
import lombok.Data;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import lk.sliit.csse.procurementsystem.Interface.Supplier;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplier;
import lk.sliit.csse.procurementsystem.Interface.SupplierRepository;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplierRepository;
@Named
@Data
public class SiteController {

 private  Site selectedSite;

 private  Site newSite;

 private  SiteItem newSiteItem;

 private  SiteManager siteManager;

 private  Supplier selectedSupplier;

 private  BlacklistedSupplier blacklistedSupplier;

@Autowired
 private  SiteRepository siteRepository;

@Autowired
 private  SiteItemRepository siteItemRepository;

@Autowired
 private  SiteManagerRepository siteManagerRepository;

@Autowired
 private  SupplierRepository supplierRepository;

@Autowired
 private  BlacklistedSupplierRepository blacklistedSupplierRepository;


public boolean addSite(){
    siteRepository.save(newSite);
    newSite = new Site();
    return true;
}


public List<Supplier> getSuppliers(){
    return supplierRepository.findByBlackListedFalse();
}


public List<SiteManager> getSiteManagers(){
    return siteManagerRepository.findAll();
}


public Site getSelectedSite(){
    return selectedSite;
}


public List<String> completeText(String query){
    List<SiteManager> siteManagers = siteManagerRepository.findByFirstnameContainingOrLastnameContaining(query, query);
    List<String> results = new ArrayList<>();
    for (SiteManager s : siteManagers) {
        results.add((s.getFirstName() + " " + s.getLastName()));
    }
    return results;
}


public void setSelectedSite(Site site){
    this.selectedSite = site;
}


public List<SiteItem> getSiteItems(){
    return siteItemRepository.findAllBySiteSiteId(selectedSite.getSiteId());
}


public void whitelistSupplier(){
    blacklistedSupplier.getSupplier().setBlackListed(false);
    Boolean isBlacklisted = blacklistedSupplier.getSupplier().isBlackListed();
    supplierRepository.setBlackListedFor(isBlacklisted, blacklistedSupplier.getSupplier().getName());
    blacklistedSupplierRepository.deleteById(blacklistedSupplier.getId());
}


public List<BlacklistedSupplier> getBlacklistedSuppliers(){
    return blacklistedSupplierRepository.findAll();
}


public List<Site> getSites(){
    return siteRepository.findAll();
}


public void addSiteManager(){
    String result = siteManager.getFirstName();
    StringTokenizer name = new StringTokenizer(result, " ");
    String firstName = name.nextToken();
    String lastName = name.nextToken();
    SiteManager selSiteManager = siteManagerRepository.findByFirstNameAndLastName(firstName, lastName);
    siteRepository.setSiteManagerFor(selSiteManager, selectedSite.getSiteId());
}


public void blacklistSupplier(){
    selectedSupplier.setBlackListed(true);
    Boolean isBlacklisted = selectedSupplier.isBlackListed();
    supplierRepository.setBlackListedFor(isBlacklisted, selectedSupplier.getName());
    blacklistedSupplier.setSupplier(selectedSupplier);
    blacklistedSupplierRepository.save(blacklistedSupplier);
}


public boolean addSiteItem(){
    newSiteItem.setSite(selectedSite);
    siteItemRepository.save(newSiteItem);
    newSiteItem = new SiteItem();
    return true;
}


}