package ink.champ.models;
 import javax.persistence;
@Entity(name = "sports")
public class Sport {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Column(length = 25)
 private  String name;

/**
 * Конструктор вида спорта
 */
public Sport() {
}/**
 * Конструктор вида спорта
 * @param name Название
 */
public Sport(String name) {
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


}