package run.halo.app.handler.theme.config.support;
 import java.util.Objects;
import java.util.Set;
import lombok.Data;
@Data
public class ThemeProperty {

 private  String id;

 private  String name;

 private  String website;

 private  String branch;

 private  String repo;

 private  UpdateStrategy updateStrategy;

 private  String description;

 private  String logo;

 private  String version;

 private  String require;

 private  Author author;

 private  String themePath;

 private  String folderName;

 private  boolean hasOptions;

 private  boolean isActivated;

 private  String screenshots;

 private  Set<String> postMetaField;

 private  Set<String> sheetMetaField;

 private  String name;

 private  String website;

 private  String avatar;


@Override
public int hashCode(){
    return Objects.hash(id);
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    ThemeProperty that = (ThemeProperty) o;
    return id.equals(that.id);
}


}