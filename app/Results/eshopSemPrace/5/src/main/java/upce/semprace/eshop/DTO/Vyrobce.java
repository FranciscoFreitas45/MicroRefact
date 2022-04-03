package upce.semprace.eshop.DTO;


import java.util.Set;

public class Vyrobce {

    private Long id;
    private String nazev;
    private String adresa;
    private Set<Produkt> produkt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Set<Produkt> getProdukt() {
        return produkt;
    }

    public void setProdukt(Set<Produkt> produkt) {
        this.produkt = produkt;
    }
}
