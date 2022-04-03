package pl.szymanski.sharelibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.szymanski.sharelibrary.enums.BookStatus;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBook {

    
    private User user;

    
    private Book book;

    
    private BookStatus status;

    private User atUser;

    @Override
    public String toString() {
        return "UserBook{" +
                "user=" + user +
                ", book=" + book +
                ", status=" + status +
                ", atUser=" + atUser +
                '}';
    }
}

