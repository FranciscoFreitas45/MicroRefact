package run.halo.app.model.entity;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity(name = "Post")
@DiscriminatorValue(value = "0")
public class Post extends BasePost{


}