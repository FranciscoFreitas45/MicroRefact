package io.swagger.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-20T13:31:05.475Z")
@Entity
@Table(name = "statistics")
@EntityListeners(AuditingEntityListener.class)
public class Statistics {

@JsonProperty("id")
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonProperty("methodcalls")
@Column
 private  Integer methodcalls;

@JsonProperty("servicename")
@Column
 private  String servicename;


public Integer setmethodcalls(Integer methodcalls){
    return this.methodcalls = methodcalls;
}


public void setServicename(String servicename){
    this.servicename = servicename;
}


public void setId(Long id){
    this.id = id;
}


@ApiModelProperty(value = "")
public Long getId(){
    return id;
}


public Statistics servicename(String servicename){
    this.servicename = servicename;
    return this;
}


public Integer getMethodcalls(){
    return methodcalls;
}


@ApiModelProperty(value = "")
public String getServicename(){
    return servicename;
}


}