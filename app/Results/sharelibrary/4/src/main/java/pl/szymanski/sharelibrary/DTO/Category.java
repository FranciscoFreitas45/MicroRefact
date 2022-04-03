package pl.szymanski.sharelibrary.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.List;
public class Category {

 private  Integer id;

 private  String name;

 private  List<Book> books;


}