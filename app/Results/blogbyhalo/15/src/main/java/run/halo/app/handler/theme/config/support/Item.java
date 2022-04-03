package run.halo.app.handler.theme.config.support;
 import java.util.List;
import java.util.Objects;
import lombok.Data;
import run.halo.app.model.enums.DataType;
import run.halo.app.model.enums.InputType;
@Data
public class Item {

 private  String name;

 private  String label;

 private  InputType type;

 private  DataType dataType;

 private  Object defaultValue;

 private  String placeholder;

 private  String description;

 private  List<Option> options;


@Override
public int hashCode(){
    return Objects.hash(name);
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    Item item = (Item) o;
    return name.equals(item.name);
}


}