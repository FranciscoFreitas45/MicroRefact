package sn.model;
 import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "tag")
@Data
public class Tag {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

@Column(name = "tag")
 private  String tag;

@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tags")
 private  Set<Post> posts;

public Tag(String tag) {
    this.tag = tag;
}
}