package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import pl.szymanski.sharelibrary.Request.BookRequest;
import pl.szymanski.sharelibrary.Request.Impl.BookRequestImpl;
import pl.szymanski.sharelibrary.DTO.Book;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
import pl.szymanski.sharelibrary.Request.CoordinatesRequest;
import pl.szymanski.sharelibrary.Request.Impl.CoordinatesRequestImpl;
import pl.szymanski.sharelibrary.DTO.Coordinates;
import pl.szymanski.sharelibrary.Request.RequirementRequest;
import pl.szymanski.sharelibrary.Request.Impl.RequirementRequestImpl;
import pl.szymanski.sharelibrary.DTO.Requirement;
import pl.szymanski.sharelibrary.Request.BookRequest;
import pl.szymanski.sharelibrary.Request.Impl.BookRequestImpl;
import pl.szymanski.sharelibrary.DTO.Book;
import pl.szymanski.sharelibrary.Request.UserRequest;
import pl.szymanski.sharelibrary.Request.Impl.UserRequestImpl;
import pl.szymanski.sharelibrary.DTO.User;
@Entity
@Data
@Table(name = "exchange")
public class Exchange {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(nullable = false)
 private  Long id;

@Column(nullable = false, name = "exchange_status")
 private  ExchangeStatus exchangeStatus;

@Column(columnDefinition = "NUMBER")
 private  Double deposit;

@Transient
 private  Book book;

@Transient
 private  User user;

@Transient
 private  Coordinates coordinates;

@Transient
 private  List<Requirement> requirements;

@Transient
 private  Book forBook;

@Transient
 private  User withUser;

@Column(name = "id")
 private Long id;

@Transient
 private BookRequest bookrequest = new BookRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private CoordinatesRequest coordinatesrequest = new CoordinatesRequestImpl();;

@Transient
 private RequirementRequest requirementrequest = new RequirementRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private BookRequest bookrequest = new BookRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


@Override
public String toString(){
    return "Exchange{" + "id=" + id + ", exchangeStatus=" + exchangeStatus + ", deposit=" + deposit + ", book=" + book + ", user=" + user + ", coordinates=" + coordinates + ", forBook=" + forBook + ", withUser=" + withUser + '}';
}


}