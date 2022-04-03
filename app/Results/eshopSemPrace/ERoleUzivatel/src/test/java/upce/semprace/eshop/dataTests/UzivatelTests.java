package upce.semprace.eshop.dataTests;

import upce.semprace.eshop.dataFarctory.UzivatelTestDataFactory;
import upce.semprace.eshop.DTO.Produkt;
import upce.semprace.eshop.entity.Uzivatel;
import upce.semprace.eshop.repository.UzivatelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(UzivatelTestDataFactory.class)
class UzivatelTests {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    @Autowired
    private UzivatelTestDataFactory uzivatelTestDataFactory;

    @Test
    void saveUzivatel() {
        List<Uzivatel> forRes = uzivatelRepository.findAll();
        int testValue = forRes.size()+1;
        uzivatelTestDataFactory.addUser1();
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == testValue);
    }

    @Test
    void saveMoreUzivatel() {
        List<Uzivatel> forRes = uzivatelRepository.findAll();
        int testValue = forRes.size()+2;
        uzivatelTestDataFactory.addUser1();
        uzivatelTestDataFactory.addUser2();
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == testValue);
    }


    @Test
    void findUzivatelViaMail() {
        uzivatelTestDataFactory.addUser1();
        uzivatelTestDataFactory.addUser2();
        Uzivatel result = uzivatelRepository.findByEmail("vv@bbb.cz");
        Assertions.assertTrue(result.getJmeno() == "abcd");
    }

    @Test
    void removeUzivatel() {
        List<Uzivatel> forRes = uzivatelRepository.findAll();
        int testValue = forRes.size()+1;
        uzivatelTestDataFactory.addUser1();
        uzivatelTestDataFactory.addUser2();
        Uzivatel result = uzivatelRepository.findByEmail("vv@bbb.cz");
        uzivatelRepository.removeUzivatelById(result.getId());
        Assertions.assertTrue(uzivatelRepository.findAll().size() == testValue);
    }

}
