package upce.semprace.eshop.dataTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import upce.semprace.eshop.dataFarctory.ProduktTestDataFactory;
import upce.semprace.eshop.DTO.Platba;
import upce.semprace.eshop.entity.Produkt;
import upce.semprace.eshop.repository.ProduktRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ProduktTestDataFactory.class)
public class ProduktTests {

    @Autowired
    private ProduktRepository produktRepository;

    @Autowired
    private ProduktTestDataFactory produktTestDataFactory;

    @Test
    void saveProdukt() {
        List<Produkt> forRes = produktRepository.findAll();
        int testValue = forRes.size()+1;
        produktTestDataFactory.addProdukt1();
        List<Produkt> all = produktRepository.findAll();
        Assertions.assertTrue(all.size() == testValue);
    }


    @Test
    void saveMoreDoprava() {
        List<Produkt> forRes = produktRepository.findAll();
        int testValue = forRes.size()+3;
        produktTestDataFactory.addProdukt1();
        produktTestDataFactory.addProdukt2();
        produktTestDataFactory.addProdukt3();
        List<Produkt> all = produktRepository.findAll();
        Assertions.assertTrue(all.size() == testValue);
    }


    @Test
    void findDopravaViaNazev() {
        produktTestDataFactory.addProdukt1();
        produktTestDataFactory.addProdukt2();
        produktTestDataFactory.addProdukt3();
        Produkt result = produktRepository.findByNazev("produkt22");
        Assertions.assertTrue(result.getNazev() == "produkt22");
    }


    @Test
    void removeDopravaViaNazev() {
        List<Produkt> forRes = produktRepository.findAll();
        int testValue = forRes.size()+2;
        produktTestDataFactory.addProdukt1();
        produktTestDataFactory.addProdukt2();
        produktTestDataFactory.addProdukt3();
        Produkt result = produktRepository.findByNazev("produkt22");
        produktRepository.removeProduktById(result.getId());
        Assertions.assertTrue(produktRepository.findAll().size() == testValue);
    }
}
