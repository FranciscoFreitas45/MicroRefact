package lk.sliit.csse.procurementsystem.models;
 import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class SiteItem implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

 private  String item;

 private  Integer quantity;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "SITE_ID")
 private  Site site;


public Integer getQuantity(){
    return quantity;
}


public void setQuantity(Integer quantity){
    this.quantity = quantity;
}


public Site getSite(){
    return site;
}


public void setId(Long id){
    this.id = id;
}


public String getItem(){
    return item;
}


public void setSite(Site site){
    this.site = site;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "SiteItem{" + "id=" + id + ", item=" + item + ", quantity=" + quantity + ", site=" + site + '}';
}


public void setItem(String item){
    this.item = item;
}


}