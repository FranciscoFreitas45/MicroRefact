package pl.szymanski.sharelibrary.DTO;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class Requirement {

   
    private Long id;

    
    private Exchange exchange;

    
    private User user;

    private LocalDateTime createTime;

    private boolean isActual;
}
