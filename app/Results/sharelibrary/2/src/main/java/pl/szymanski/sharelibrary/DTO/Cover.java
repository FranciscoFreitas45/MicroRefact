package pl.szymanski.sharelibrary.DTO;
 import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence;
import java.util.Arrays;
import java.util.Objects;
public class Cover {

 private  Long id;

 private  String name;

 private  String type;

 private  byte[] data;

 private  Book book;

public Cover(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
}
}