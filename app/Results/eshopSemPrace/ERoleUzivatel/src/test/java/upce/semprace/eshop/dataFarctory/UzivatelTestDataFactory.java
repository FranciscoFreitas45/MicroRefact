package upce.semprace.eshop.dataFarctory;

import upce.semprace.eshop.entity.Uzivatel;
import upce.semprace.eshop.repository.UzivatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UzivatelTestDataFactory {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    public Uzivatel addUser1() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        uzivatelRepository.save(user);
        return user;
    }

    public Uzivatel addUser2() {
        Uzivatel userS = new Uzivatel();
        userS.setJmeno("abcd");
        userS.setPrijmeni("cba");
        userS.setEmail("vv@bbb.cz");
        userS.setHeslo("pass");
        userS.setAdresa("konecna 123");
        uzivatelRepository.save(userS);
        return userS;
    }
}
