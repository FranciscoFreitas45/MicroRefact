package goorum.goorum.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.io.Serializable;
public class Category implements Serializable{

 private  Long categoryId;

 private  String categoryName;


}