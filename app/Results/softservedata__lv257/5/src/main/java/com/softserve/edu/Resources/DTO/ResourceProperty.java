package com.softserve.edu.Resources.DTO;
 import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence;
import java.util.Optional;
public class ResourceProperty implements Comparable<ResourceProperty>{

 transient  long idgen;

 private  Long id;

 private  String columnName;

 private  String title;

 private  String units;

 private  String unitsShort;

 private  String hint;

 private  String pattern;

 private  ValueType valueType;

 private  boolean multivalued;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public ResourceProperty() {
}public ResourceProperty(String title) {
    this.columnName = title;
    this.title = title;
    this.id = idgen++;
}
public String getUnitsShort(){
    return unitsShort;
}


public String getColumnName(){
    return columnName;
}


public long getId(){
    return id;
}


public String getDescription(){
    return units == null || units.isEmpty() ? title : String.join(", ", title, units);
}


public String getTitle(){
    return title;
}


public String getUnits(){
    return units;
}


public String getPattern(){
    return pattern;
}


public ValueType getValueType(){
    return valueType;
}


public String getHint(){
    return hint;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ResourceProperty that = (ResourceProperty) o;
    if (!columnName.equals(that.columnName))
        return false;
    return units != null ? units.equals(that.units) : that.units == null;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/equals"))

.queryParam("o",o)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


@Override
public int compareTo(ResourceProperty o){
    return this.getColumnName().compareToIgnoreCase(o.getColumnName());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/compareTo"))

.queryParam("o",o)
;
int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}