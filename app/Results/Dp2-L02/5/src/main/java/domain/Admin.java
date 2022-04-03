package domain;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "polarity") })
public class Admin extends Actor{

public Admin() {
}
}