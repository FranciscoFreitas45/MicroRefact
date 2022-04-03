package pl.szymanski.sharelibrary.DTO;

import lombok.Data;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
public class Exchange {

   
    private Long id;

   
    private ExchangeStatus exchangeStatus;

    
    private Double deposit;

   
    private Book book;

    
    private User user;

    
    private Coordinates coordinates;

    
    private List<Requirement> requirements;

   
    private Book forBook;

    
    private User withUser;

    @Override
    public String toString() {
        return "Exchange{" +
                "id=" + id +
                ", exchangeStatus=" + exchangeStatus +
                ", deposit=" + deposit +
                ", book=" + book +
                ", user=" + user +
                ", coordinates=" + coordinates +
                ", forBook=" + forBook +
                ", withUser=" + withUser +
                '}';
    }
}
