package upce.semprace.eshop.dataTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import upce.semprace.eshop.dataFarctory.VyrobceTestDataTest;
import upce.semprace.eshop.DTO.Uzivatel;
import upce.semprace.eshop.entity.Vyrobce;
import upce.semprace.eshop.repository.VyrobceRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(VyrobceTestDataTest.class)
public class VyrobceTests {
    @Autowired
    private VyrobceRepository vyrobceRepository;

    @Autowired
    private VyrobceTestDataTest vyrobceTestDataTest;

    @Test
    void saveVyrobce() {
        List<Vyrobce> forRes = vyrobceRepository.findAll();
        int testValue = forRes.size()+1;
        vyrobceTestDataTest.addVyrobce1();
        List<Vyrobce> all = vyrobceRepository.findAll();
        Assertions.assertTrue(all.size() == testValue);
    }


    @Test
    void saveMoreVyrobce() {
        List<Vyrobce> forRes = vyrobceRepository.findAll();
        int testValue = forRes.size()+3;
        vyrobceTestDataTest.addVyrobce1();
        vyrobceTestDataTest.addVyrobce2();
        vyrobceTestDataTest.addVyrobce3();
        List<Vyrobce> all = vyrobceRepository.findAll();
        Assertions.assertTrue(all.size() == testValue);
    }


    @Test
    void findVyrobceViaNazev() {
        vyrobceTestDataTest.addVyrobce1();
        vyrobceTestDataTest.addVyrobce2();
        vyrobceTestDataTest.addVyrobce3();
        Vyrobce result = vyrobceRepository.findByNazev("vyrobce22");
        Assertions.assertTrue(result.getNazev() == "vyrobce22");
    }


    @Test
    void removeVyrobceViaNazev() {
        List<Vyrobce> forRes = vyrobceRepository.findAll();
        int testValue = forRes.size()+2;
        vyrobceTestDataTest.addVyrobce1();
        vyrobceTestDataTest.addVyrobce2();
        vyrobceTestDataTest.addVyrobce3();
        Vyrobce result = vyrobceRepository.findByNazev("vyrobce22");
        vyrobceRepository.removeVyrobceById(result.getId());
        Assertions.assertTrue(vyrobceRepository.findAll().size() == testValue);
    }
}
