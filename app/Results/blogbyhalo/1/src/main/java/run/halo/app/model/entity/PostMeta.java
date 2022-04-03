package run.halo.app.model.entity;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
@Entity(name = "PostMeta")
@DiscriminatorValue("0")
@EqualsAndHashCode(callSuper = true)
public class PostMeta extends BaseMeta{


}