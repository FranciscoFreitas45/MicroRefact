package run.halo.app.model.entity;
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
import org.hibernate.annotations.GenericGenerator;
@Data
@Entity
@Table(name = "tags", indexes = { @Index(name = "tags_name", columnList = "name") })
@ToString
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseEntity{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
@GenericGenerator(name = "custom-id", strategy = "run.halo.app.model.entity.support.CustomIdGenerator")
 private  Integer id;

@Column(name = "name", nullable = false)
 private  String name;

@Deprecated
@Column(name = "slug_name")
 private  String slugName;

@Column(name = "slug", unique = true)
 private  String slug;

@Column(name = "thumbnail", length = 1023)
 private  String thumbnail;


}