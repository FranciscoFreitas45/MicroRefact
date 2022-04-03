package run.halo.app.model.entity;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity(name = "Sheet")
@DiscriminatorValue("1")
public class Sheet extends BasePost{


}