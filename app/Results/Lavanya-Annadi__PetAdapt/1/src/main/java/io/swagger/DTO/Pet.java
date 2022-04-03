package io.swagger.DTO;
 import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
public class Pet {

 private  Long id;

 private  String name;

 private  String breed;

 private  Integer age;

 private  String origin;

 private  String color;

 private  Float hieght;

 private  Integer life_span;

 private  String description;

 private  Provider provider;

 private  Medic medic;

 private  List<String> images;


public Integer getAge(){
    return age;
}


@ApiModelProperty(example = "doggie", required = true, value = "")
@NotNull
public String getName(){
    return name;
}


public String getBreed(){
    return breed;
}


@ApiModelProperty(value = "")
public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public Provider getProvider(){
    return provider;
}


public Integer getLife_span(){
    return life_span;
}


public List<String> getImages(){
    return images;
}


public Float getHieght(){
    return hieght;
}


public String getOrigin(){
    return origin;
}


public String getColor(){
    return color;
}


public Medic getMedic(){
    return medic;
}


}