package br.com.fatecmogidascruzes.domain;
 import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public class NamedEntity extends EntityImpl{

 protected  long serialVersionUID;

@Basic
@Column(name = "name", nullable = true, length = 100)
 protected  String name;


}