package main.model;
 import lombok.Getter;
import lombok.Setter;
import javax.persistence;
@Entity
@Table(name = "global_settings")
@Getter
@Setter
public class GlobalSetting {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Column(name = "code", nullable = false)
 private  String code;

@Column(name = "name", nullable = false)
 private  String name;

@Column(name = "value", nullable = false)
 private  String value;


}