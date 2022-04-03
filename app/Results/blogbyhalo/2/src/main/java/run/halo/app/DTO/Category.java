package run.halo.app.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
public class Category extends BaseEntity{

 private  Integer id;

 private  String name;

 private  String slugName;

 private  String slug;

 private  String description;

 private  String thumbnail;

 private  Integer parentId;

 private  String password;


}