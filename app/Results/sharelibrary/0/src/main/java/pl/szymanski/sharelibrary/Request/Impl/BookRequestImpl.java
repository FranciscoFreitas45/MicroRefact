package pl.szymanski.sharelibrary.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.DTO.Book;
import pl.szymanski.sharelibrary.Request.BookRequest;
public class BookRequestImpl implements BookRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}