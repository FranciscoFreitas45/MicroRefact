package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence;
import java.util.Optional;
@Entity
@Table(name = "RESOURCE_PROPERTIES", uniqueConstraints = @UniqueConstraint(name = "unq_title_units", columnNames = { "Title", "Units" }))
public class ResourceProperty implements Comparable<ResourceProperty>{

 transient  long idgen;

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  Long id;

@Column(name = "Column_Name", unique = true, nullable = false)
@NotEmpty
 private  String columnName;

@Column(name = "Title", nullable = false)
@NotEmpty
 private  String title;

@Column(name = "Units")
 private  String units;

@Column(name = "Units_short")
 private  String unitsShort;

@Column(name = "Hint")
 private  String hint;

@Column(name = "Regex", nullable = false)
 private  String pattern;

@Enumerated(EnumType.STRING)
@Column(name = "Value_Type")
 private  ValueType valueType;

@Column(name = "Multivalued")
 private  boolean multivalued;

public ResourceProperty() {
}public ResourceProperty(String title) {
    this.columnName = title;
    this.title = title;
    this.id = idgen++;
}
public ResourceProperty setColumnName(String columnName){
    this.columnName = columnName;
    return this;
}


public Class<?> valueClassFor(String classDescription){
    final Optional<ValueType> valueType = ValueType.forName(classDescription);
    if (!valueType.isPresent()) {
        throw new IllegalArgumentException("Inappropriate type requested");
    }
    return valueType.map(vt -> vt.clazz).get();
}


public void setHint(String hint){
    this.hint = hint;
}


public boolean isMultivalued(){
    return multivalued;
}


public String getUnitsShort(){
    return unitsShort;
}


public void setUnitsShort(String unitsShort){
    this.unitsShort = unitsShort;
}


public String getColumnName(){
    return columnName;
}


public ResourceProperty setTitle(String title){
    this.title = title;
    return this;
}


public long getId(){
    return id;
}


@Override
public int compareTo(ResourceProperty o){
    return this.getColumnName().compareToIgnoreCase(o.getColumnName());
}


public ResourceProperty setMultivalued(boolean multivalued){
    this.multivalued = multivalued;
    return this;
}


public String getDescription(){
    return units == null || units.isEmpty() ? title : String.join(", ", title, units);
}


public String getTitle(){
    return title;
}


public ResourceProperty setValueType(ValueType valueType){
    this.valueType = valueType;
    return this;
}


@Override
public int hashCode(){
    int result = columnName.hashCode();
    result = 31 * result + (units != null ? units.hashCode() : 0);
    return result;
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
}


public String getUnits(){
    return units;
}


public ResourceProperty setId(Long id){
    this.id = id;
    return this;
}


public ResourceProperty setPattern(String pattern){
    this.pattern = pattern;
    return this;
}


@Override
public String toString(){
    return units == null ? columnName : String.join(", ", columnName, units);
}


public String getPattern(){
    return pattern;
}


public ValueType getValueType(){
    return valueType;
}


public ResourceProperty setUnits(String units){
    this.units = units;
    return this;
}


public String getHint(){
    return hint;
}


}