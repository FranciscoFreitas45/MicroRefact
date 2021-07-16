import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Dish {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "DISH_ID")
 private  Long dishId;

@Column(name = "ENGLISH_NAME", length = 50)
 private  String englishName;

@Column(name = "POLISH_NAME", length = 50)
 private  String polishName;

@Column(name = "PRICE")
 private  double price;

@Column(name = "IMAGE")
@Type(type = "text")
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


public void setPolishName(String polishName){
    this.polishName = polishName;
}


public void setDishId(Long dishId){
    this.dishId = dishId;
}


public void setPrice(double price){
    this.price = price;
}


public String getImage(){
    return image;
}


public double getPrice(){
    return this.price;
}


public void setEnglishName(String englishName){
    this.englishName = englishName;
}


public void setImage(String image){
    this.image = image;
}


}