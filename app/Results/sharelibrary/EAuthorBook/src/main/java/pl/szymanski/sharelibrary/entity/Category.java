package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.util.List;
@Data
@Entity
@Table(name = "category")
public class Category {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

 private  String name;

@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
 private  List<Book> books;


@Override
public String toString(){
    return "Category{" + "id=" + id + ", name='" + name + '\'' + '}';
}


}