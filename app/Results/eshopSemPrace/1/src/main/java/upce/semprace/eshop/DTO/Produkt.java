package upce.semprace.eshop.DTO;

import upce.semprace.eshop.DTO.NakoupenaPolozka;
public class Produkt {
    private Long id;
    private String nazev;
    private String popis;
    private int cena;
    private int slevaProcenta;
    private boolean vNabidce;
    private String cestaKObrazku;
    private NakoupenaPolozka nakoupenaPolozka;
    
    private Vyrobce vyrobce;

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

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getSlevaProcenta() {
        return slevaProcenta;
    }

    public void setSlevaProcenta(int slevaProcenta) {
        this.slevaProcenta = slevaProcenta;
    }

    public boolean isvNabidce() {
        return vNabidce;
    }

    public void setvNabidce(boolean vNabidce) {
        this.vNabidce = vNabidce;
    }

    public String getCestaKObrazku() {
        return cestaKObrazku;
    }

    public void setCestaKObrazku(String cestaKObrazku) {
        this.cestaKObrazku = cestaKObrazku;
    }

    public Vyrobce getVyrobce() {
        return vyrobce;
    }

    public void setVyrobce(Vyrobce vyrobce) {
        this.vyrobce = vyrobce;
    }

    public NakoupenaPolozka getNakoupenaPolozka() {
        return nakoupenaPolozka;
    }

    public void setNakoupenaPolozka(NakoupenaPolozka nakoupenaPolozka) {
        this.nakoupenaPolozka = nakoupenaPolozka;
    }
}
