package upce.semprace.eshop.DTO;
import java.util.Set;


public class Platba {
    private Long id;
    private String popis;
    private Double prevod;


    private Set<Nakup> nakup;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Double getPrevod() {
        return prevod;
    }

    public void setPrevod(Double prevod) {
        this.prevod = prevod;
    }

    public Set<Nakup> getNakup() {
        return nakup;
    }

    public void setNakup(Set<Nakup> nakup) {
        this.nakup = nakup;
    }
}
