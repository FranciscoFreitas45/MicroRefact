package upce.semprace.eshop.dataFarctory;

import upce.semprace.eshop.entity.Platba;
import upce.semprace.eshop.repository.PlatbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlatbaTestDataFactory {

    @Autowired
    PlatbaRepository platbaRepository;

    public Platba addPlatba1() {
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        return pay;
    }

    public Platba addPlatba2() {
        Platba pay2 = new Platba();
        pay2.setPrevod(10.5);
        pay2.setPopis("ssdd");
        platbaRepository.save(pay2);
        return pay2;
    }

    public Platba addPlatba3() {
        Platba pay3 = new Platba();
        pay3.setPrevod(15.0);
        pay3.setPopis("ffdd");
        platbaRepository.save(pay3);
        return pay3;
    }
}
