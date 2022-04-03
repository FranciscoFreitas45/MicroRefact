package upce.semprace.eshop.servicesTests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import upce.semprace.eshop.dataFarctory.Creator;
import upce.semprace.eshop.dataFarctory.ProduktTestDataFactory;
import upce.semprace.eshop.DTO.*;
import upce.semprace.eshop.Interface.*;
import upce.semprace.eshop.services.CartService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"upce.semprace.eshop.services"})
@Import({Creator.class, ProduktTestDataFactory.class})
public class CartTests {
    @Autowired
    private CartService cartService;

    @Autowired
    private NakupRepository nakupRepository;

    @Autowired
    private NakoupenaPolozkaRepository nakoupenaPolozkaRepository;

    @Autowired
    private ProduktTestDataFactory produktTestDataFactory;

    @Autowired
    Creator creator;

    @Test
    public void makeOrder() {
        Uzivatel uzivatel = (Uzivatel) creator.saveEntity(new Uzivatel());
        Doprava doprava = (Doprava) creator.saveEntity(new Doprava());
        Platba platba = (Platba) creator.saveEntity(new Platba());
        Produkt produkt1 = produktTestDataFactory.addProdukt1();
        Produkt produkt2 = produktTestDataFactory.addProdukt2();
        Produkt produkt3 = produktTestDataFactory.addProdukt3();
        List<Produkt> produktList = new ArrayList<>();
        produktList.add(produkt1);
        produktList.add(produkt2);
        produktList.add(produkt3);
        cartService.order(uzivatel.getId(), doprava.getId(), platba.getId(), produktList);
        List<Nakup> allNakup = nakupRepository.findAll();
        List<NakoupenaPolozka> allPolozka = nakoupenaPolozkaRepository.findAll();
        Assertions.assertTrue(allNakup.size() == 1);
        Assertions.assertTrue(allPolozka.size() == 3);
    }
}
