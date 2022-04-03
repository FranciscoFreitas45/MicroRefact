package upce.semprace.eshop.DTO;

import java.util.Set;

public class Doprava {
    private Long id;
    private String popis;
    private Integer cena;

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

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Set<Nakup> getNakup() {
        return nakup;
    }

    public void setNakup(Set<Nakup> nakup) {
        this.nakup = nakup;
    }
}
