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