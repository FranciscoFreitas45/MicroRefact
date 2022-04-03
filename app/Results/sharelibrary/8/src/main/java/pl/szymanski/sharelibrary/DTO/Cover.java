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

 private Long id;

public Cover(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
}
@Override
public int hashCode(){
    return Objects.hash(id, name, type);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    Cover cover = (Cover) o;
    return Objects.equals(id, cover.id) && Objects.equals(name, cover.name) && Objects.equals(type, cover.type) && Arrays.equals(data, cover.data);
}


@Override
public String toString(){
    return "Cover{" + "name='" + name + '\'' + '}';
}


}