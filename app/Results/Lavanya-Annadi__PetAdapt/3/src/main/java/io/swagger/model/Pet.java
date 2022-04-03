package io.swagger.model;
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
import io.swagger.Request.ProviderRequest;
import io.swagger.Request.Impl.ProviderRequestImpl;
import io.swagger.DTO.Provider;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "pets")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "medic" }, allowGetters = false)
public class Pet {

@JsonProperty("id")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonProperty("name")
@Column
 private  String name;

@JsonProperty("breed")
@Column
 private  String breed;

@JsonProperty("age")
@Column
 private  Integer age;

@JsonProperty("origin")
@Column
 private  String origin;

@JsonProperty("color")
@Column
 private  String color;

@JsonProperty("hieght")
@Column
 private  Float hieght;

@JsonProperty("life_span")
@Column
 private  Integer life_span;

@JsonProperty("description")
@Column
 private  String description;

@Transient
 private  Provider provider;

@JsonProperty("medic")
@OneToOne(fetch = FetchType.LAZY)
 private  Medic medic;

@ElementCollection
 private  List<String> images;

@Column(name = "id")
 private Long id;

@Transient
 private ProviderRequest providerrequest = new ProviderRequestImpl();;


public void setName(String name){
    this.name = name;
}


public Integer getAge(){
    return age;
}


@ApiModelProperty(example = "doggie", required = true, value = "")
@NotNull
public String getName(){
    return name;
}


public void setProvider(Provider provider){
 providerrequest.setProvider(provider,this.id);
}



public void setOrigin(String origin){
    this.origin = origin;
}


public String getBreed(){
    return breed;
}


public void setHieght(Float hieght){
    this.hieght = hieght;
}


@ApiModelProperty(value = "")
public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public Provider getProvider(){
  this.provider = providerrequest.getProvider(this.id);
return this.provider;
}


public void setId(Long id){
    this.id = id;
}


public Integer getLife_span(){
    return life_span;
}


public void setImages(List<String> images){
    this.images = images;
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


public void setLife_span(Integer life_span){
    this.life_span = life_span;
}


public void setColor(String color){
    this.color = color;
}


public void setBreed(String breed){
    this.breed = breed;
}


@ApiModelProperty(value = "")
@Valid
public Pet name(String name){
    this.name = name;
    return this;
}


public Medic getMedic(){
    return medic;
}


public void setMedic(Medic medic){
    this.medic = medic;
}


public void setAge(Integer age){
    this.age = age;
}


}