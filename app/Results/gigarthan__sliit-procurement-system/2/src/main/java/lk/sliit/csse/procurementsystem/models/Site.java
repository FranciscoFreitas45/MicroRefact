package lk.sliit.csse.procurementsystem.models;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Site implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long siteId;

 private  String siteName;

 private  String address;

 private  Float storageCapacity;

 private  Float currentCapacity;

@OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<SiteItem> siteItems;

@OneToOne
 private  SiteManager siteManager;


public void setSiteId(Long siteId){
    this.siteId = siteId;
}


public void setAddress(String address){
    this.address = address;
}


public void setStorageCapacity(Float storageCapacity){
    this.storageCapacity = storageCapacity;
}


public Long getSiteId(){
    return siteId;
}


public void setCurrentCapacity(Float currentCapacity){
    this.currentCapacity = currentCapacity;
}


public List<SiteItem> getSiteItems(){
    return siteItems;
}


public Float getCurrentCapacity(){
    return currentCapacity;
}


public void setSiteItems(List<SiteItem> siteItems){
    this.siteItems = siteItems;
}


public void setSiteManager(SiteManager siteManager){
    this.siteManager = siteManager;
}


public void setSiteName(String siteName){
    this.siteName = siteName;
}


public String getAddress(){
    return address;
}


@Override
public String toString(){
    return "Site{" + "siteId=" + siteId + ", siteName=" + siteName + ", address=" + address + ", storageCapacity=" + storageCapacity + ", currentCapacity=" + currentCapacity + ", siteItems=" + siteItems + ", siteManager=" + siteManager + '}';
}


public SiteManager getSiteManager(){
    return siteManager;
}


public String getSiteName(){
    return siteName;
}


public Float getStorageCapacity(){
    return storageCapacity;
}


}