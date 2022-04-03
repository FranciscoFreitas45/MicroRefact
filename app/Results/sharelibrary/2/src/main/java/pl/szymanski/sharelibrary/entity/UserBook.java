package pl.szymanski.sharelibrary.entity;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.szymanski.sharelibrary.enums.BookStatus;
import javax.persistence;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_book")
@IdClass(UserBookPK.class)
public class UserBook {

@Id
@ManyToOne
@JoinColumn(name = "user_id", referencedColumnName = "id")
 private  User user;

@Id
@ManyToOne
@JoinColumn(name = "book_id", referencedColumnName = "id")
 private  Book book;

@Column(name = "book_status")
 private  BookStatus status;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "atUserId")
 private  User atUser;


@Override
public String toString(){
    return "UserBook{" + "user=" + user + ", book=" + book + ", status=" + status + ", atUser=" + atUser + '}';
}


}