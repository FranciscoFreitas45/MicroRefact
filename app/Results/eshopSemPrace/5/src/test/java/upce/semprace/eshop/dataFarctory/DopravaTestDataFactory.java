package upce.semprace.eshop.dataFarctory;

import upce.semprace.eshop.entity.Doprava;
import upce.semprace.eshop.repository.DopravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DopravaTestDataFactory {

    @Autowired
    private DopravaRepository dopravaRepository;
    public Doprava saveNewDoprava1() {
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        return deliver;
    }
    public Doprava saveNewDoprava2() {
        Doprava deliver2 = new Doprava();
        deliver2.setCena(109);
        deliver2.setPopis("blabla");
        dopravaRepository.save(deliver2);
        return deliver2;
    }

    public Doprava saveNewDoprava3() {
        Doprava deliver3 = new Doprava();
        deliver3.setCena(510);
        deliver3.setPopis("blabla3");
        dopravaRepository.save(deliver3);
        return deliver3;
    }

    public Doprava saveDoprava(Doprava doprava) {
        if (doprava.getPopis()==null) doprava.setPopis("testDoprava");
        if (doprava.getCena()==null) doprava.setCena(99999);
        dopravaRepository.save(doprava);
        return doprava;
    }
}
