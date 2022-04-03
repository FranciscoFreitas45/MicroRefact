package switchtwentytwenty.project.datamodel;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence;
@Entity
@Table(name = "Address")
@NoArgsConstructor
public class AddressJPA {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

@Getter
@Column(name = "houseNumber")
 private  String houseNumber;

@Getter
@Column(name = "street")
 private  String street;

@Getter
@Column(name = "city")
 private  String city;

@Getter
@Column(name = "zipCode")
 private  String zipCode;

@Getter
@Column(name = "country")
 private  String country;

@Getter
@OneToOne(mappedBy = "address")
 private  PersonJPA personJPA;

// Constructor method
/**
 * Constructor.
 *
 * @param houseNumber - house number
 * @param street      - street
 * @param city        - city
 * @param zipCode     - zip code
 * @param country     - country
 * @param personJPA   - person JPA
 */
public AddressJPA(String houseNumber, String street, String city, String zipCode, String country, PersonJPA personJPA) {
    this.houseNumber = houseNumber;
    this.street = street;
    this.city = city;
    this.zipCode = zipCode;
    this.country = country;
    this.personJPA = personJPA;
}
}