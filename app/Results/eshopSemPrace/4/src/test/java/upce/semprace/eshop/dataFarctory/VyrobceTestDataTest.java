package upce.semprace.eshop.dataFarctory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upce.semprace.eshop.entity.Vyrobce;
import upce.semprace.eshop.repository.VyrobceRepository;

@Component
public class VyrobceTestDataTest {
    @Autowired
    private VyrobceRepository vyrobceRepository;

    public Vyrobce addVyrobce1() {
        Vyrobce vyrobce = new Vyrobce();
        vyrobce.setNazev("vyrobce11");
        vyrobce.setAdresa("tamATam1");
        vyrobceRepository.save(vyrobce);
        return vyrobce;
    }

    public Vyrobce addVyrobce2() {
        Vyrobce vyrobce = new Vyrobce();
        vyrobce.setNazev("vyrobce22");
        vyrobce.setAdresa("tamATam2");
        vyrobceRepository.save(vyrobce);
        return vyrobce;
    }

    public Vyrobce addVyrobce3() {
        Vyrobce vyrobce = new Vyrobce();
        vyrobce.setNazev("vyrobce33");
        vyrobce.setAdresa("tamATam3");
        vyrobceRepository.save(vyrobce);
        return vyrobce;
    }
}
