package upce.semprace.eshop.dto;
 import upce.semprace.eshop.DTO.Produkt;
import java.util.List;
public class PridejZmenNakupDto {

 private  Long doprava;

 private  Long platba;

 private  List<Produkt> polozky;


public Long getPlatba(){
    return platba;
}


public List<Produkt> getPolozky(){
    return polozky;
}


public void setDoprava(Long doprava){
    this.doprava = doprava;
}


public void setPlatba(Long platba){
    this.platba = platba;
}


public void setPolozky(List<Produkt> polozky){
    this.polozky = polozky;
}


public Long getDoprava(){
    return doprava;
}


}