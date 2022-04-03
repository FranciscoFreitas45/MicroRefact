package run.halo.app.model.entity;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity(name = "SheetComment")
@DiscriminatorValue("1")
public class SheetComment extends BaseComment{


}