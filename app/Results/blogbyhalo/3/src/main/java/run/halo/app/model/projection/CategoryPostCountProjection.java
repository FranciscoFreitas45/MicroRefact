package run.halo.app.model.projection;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPostCountProjection {

 private  Long postCount;

 private  Integer categoryId;


}