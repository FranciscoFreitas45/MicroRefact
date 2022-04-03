package pl.szymanski.sharelibrary.DTO;
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
public class Exchange {

 private  Long id;

 private  ExchangeStatus exchangeStatus;

 private  Double deposit;

 private  Book book;

 private  User user;

 private  Coordinates coordinates;

 private  List<Requirement> requirements;

 private  Book forBook;

 private  User withUser;

 private Long id;

 private Long id;

 private Long id;

 private Long id;

 private Long id;


}