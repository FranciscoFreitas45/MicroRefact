package sn.DTO;
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
public class Tag {

 private  long id;

 private  String tag;

 private  Set<Post> posts;

public Tag(String tag) {
    this.tag = tag;
}
}