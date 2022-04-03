package upce.semprace.eshop.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import upce.semprace.eshop.entity.Doprava;

public class Nakup {
    private Long id;
    private Date datumVytvoreni;
    private Integer objednavka;
    private Boolean stav;

    private Uzivatel uzivatel;
    private Doprava doprava;
    private Platba platba;
    private Set<NakoupenaPolozka> nakoupenaPolozka;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDatumVytvoreni() {
        return datumVytvoreni;
    }

    public void setDatumVytvoreni(Date datumVytvoreni) {
        this.datumVytvoreni = datumVytvoreni;
    }

    public Integer getObjednavka() {
        return objednavka;
    }

    public void setObjednavka(Integer objednavka) {
        this.objednavka = objednavka;
    }

    public Boolean getStav() {
        return stav;
    }

    public void setStav(Boolean stav) {
        this.stav = stav;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Doprava getDoprava() {
        return doprava;
    }

    public void setDoprava(Doprava doprava) {
        this.doprava = doprava;
    }

    public Platba getPlatba() {
        return platba;
    }

    public void setPlatba(Platba platba) {
        this.platba = platba;
    }

    public Set<NakoupenaPolozka> getNakoupenaPolozka() {
        return nakoupenaPolozka;
    }

    public void setNakoupenaPolozka(Set<NakoupenaPolozka> nakoupenaPolozka) {
        this.nakoupenaPolozka = nakoupenaPolozka;
    }
}
