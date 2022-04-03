package upce.semprace.eshop.services;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upce.semprace.eshop.entity.NakoupenaPolozka;
import upce.semprace.eshop.entity.Nakup;
import upce.semprace.eshop.entity.Produkt;
import upce.semprace.eshop.repository;
import java.util;
import java.util.function.Function;
import java.util.stream.Collectors;
import upce.semprace.eshop.Interface.ProduktRepository;
import upce.semprace.eshop.Interface.DopravaRepository;
import upce.semprace.eshop.Interface.PlatbaRepository;
import upce.semprace.eshop.Interface.UzivatelRepository;
@Service
@Transactional
public class CartServiceImpl implements CartService{

 private  Map<Integer,Long> kosik;

@Autowired
 private  ProduktRepository produktRepository;

@Autowired
 private  NakupRepository nakupRepository;

@Autowired
 private  NakoupenaPolozkaRepository nakoupenaPolozkaRepository;

@Autowired
 private  DopravaRepository dopravaRepository;

@Autowired
 private  PlatbaRepository platbaRepository;

@Autowired
 private  UzivatelRepository uzivatelRepository;

public CartServiceImpl(ProduktRepository produktRepository, NakupRepository nakupRepository, NakoupenaPolozkaRepository nakoupenaPolozkaRepository, DopravaRepository dopravaRepository, PlatbaRepository platbaRepository, UzivatelRepository uzivatelRepository) {
    this.produktRepository = produktRepository;
    this.nakupRepository = nakupRepository;
    this.nakoupenaPolozkaRepository = nakoupenaPolozkaRepository;
    this.dopravaRepository = dopravaRepository;
    this.platbaRepository = platbaRepository;
    this.uzivatelRepository = uzivatelRepository;
    kosik = new HashMap<>();
}
@Override
public void order(Long idUzivatel,Long idDoprava,Long idPlatba,List<Produkt> polozky){
    Date datum = new Date();
    Nakup nakup = new Nakup();
    nakup.setDoprava(dopravaRepository.findById(idDoprava).get());
    nakup.setUzivatel(uzivatelRepository.findById(idUzivatel).get());
    nakup.setPlatba(platbaRepository.findById(idPlatba).get());
    nakup.setDatumVytvoreni(datum);
    nakup.setObjednavka(-((int) datum.getTime()));
    nakup.setStav(Boolean.FALSE);
    nakupRepository.save(nakup);
    List<Integer> intList = new ArrayList<Integer>(polozky.size());
    for (Produkt i : polozky) {
        intList.add(i.getId().intValue());
    }
    Map<Integer, Long> counted = intList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    kosik = counted;
    for (Map.Entry<Integer, Long> entry : kosik.entrySet()) {
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setProdukt(produktRepository.findById(entry.getKey().longValue()).get());
        nakoupenaPolozka.setMnozstvi(entry.getValue().intValue());
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        nakoupenaPolozka.setNakup(nakup);
        nakupRepository.save(nakup);
    }
    kosik.clear();
}


}