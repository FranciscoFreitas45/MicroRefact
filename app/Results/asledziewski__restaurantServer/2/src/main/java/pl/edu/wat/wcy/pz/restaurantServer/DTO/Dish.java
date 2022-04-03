package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence;
public class Dish {

 private  Long dishId;

 private  String englishName;

 private  String polishName;

 private  double price;

 private  String image;

public Dish(String englishName, String polishName, double price, String image) {
    this.englishName = englishName;
    this.polishName = polishName;
    this.price = price;
    this.image = image;
}
public Long getDishId(){
    return dishId;
}


public String getEnglishName(){
    return englishName;
}


public String getPolishName(){
    return polishName;
}


public String getImage(){
    return image;
}


public double getPrice(){
    return this.price;
}


}