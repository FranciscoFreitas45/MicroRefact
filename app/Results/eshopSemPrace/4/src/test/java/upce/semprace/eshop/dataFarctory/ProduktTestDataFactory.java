package upce.semprace.eshop.dataFarctory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upce.semprace.eshop.entity.Produkt;
import upce.semprace.eshop.repository.ProduktRepository;

@Component
public class ProduktTestDataFactory {
    @Autowired
    private ProduktRepository produktRepository;

    public Produkt addProdukt1() {
        Produkt produkt = new Produkt();
        produkt.setNazev("produkt11");
        produkt.setPopis("popis popis popis 1");
        produkt.setCena(500);
        produkt.setSlevaProcenta(0);
        produkt.setvNabidce(true);
        produkt.setCestaKObrazku("cesta1");
        produktRepository.save(produkt);
        return produkt;
    }

    public Produkt addProdukt2() {
        Produkt produkt = new Produkt();
        produkt.setNazev("produkt22");
        produkt.setPopis("popis popis popis 2");
        produkt.setCena(100);
        produkt.setSlevaProcenta(50);
        produkt.setvNabidce(true);
        produkt.setCestaKObrazku("cesta2");
        produktRepository.save(produkt);
        return produkt;
    }

    public Produkt addProdukt3() {
        Produkt produkt = new Produkt();
        produkt.setNazev("produkt33");
        produkt.setPopis("popis popis popis 3");
        produkt.setCena(1000);
        produkt.setSlevaProcenta(10);
        produkt.setvNabidce(true);
        produkt.setCestaKObrazku("cesta3");
        produktRepository.save(produkt);
        return produkt;
    }
}
