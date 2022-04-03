package gov.cdc.nccdphp.domain;
 import lombok.Data;
import javax.persistence;
@Entity
@Data
public class Division {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Integer id;

@Version
 private  Integer version;

@Column(unique = true)
 private  String name;

@Column(unique = true)
 private  String abbreviation;


}