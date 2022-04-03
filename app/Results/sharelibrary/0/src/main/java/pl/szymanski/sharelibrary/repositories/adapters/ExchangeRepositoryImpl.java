package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import pl.szymanski.sharelibrary.repositories.jpa.ExchangeJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.ExchangeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Repository
public class ExchangeRepositoryImpl implements ExchangeRepository{

 private  ExchangeJPARepository exchangeJPARepository;


@Override
public Exchange saveExchange(Exchange exchange){
    return exchangeJPARepository.saveAndFlush(exchange);
}


@Override
public List<Exchange> getExchangeByCoordinatesAndRadius(Double latitude,Double longitude,Double radius){
    return exchangeJPARepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);
}


@Override
public List<Exchange> getAll(){
    return exchangeJPARepository.findAll();
}


@Override
public List<Exchange> getExchangeByStatus(ExchangeStatus exchangeStatus){
    return exchangeJPARepository.findAllByExchangeStatus(exchangeStatus);
}


@Override
public List<Exchange> getExchangeByBoundingCoordinates(double latMin,double latMax,double longMin,double longMax){
    return exchangeJPARepository.findByBoundingCoordinates(latMin, latMax, longMin, longMax);
}


@Override
public Optional<Exchange> getExchangeById(Long id){
    return exchangeJPARepository.findById(id);
}


@Override
public List<Exchange> getExchangesLinkedWithUser(Long userId){
    return exchangeJPARepository.findAllLinkedWithUser(userId).stream().filter(it -> it.getExchangeStatus().equals(ExchangeStatus.DURING)).collect(Collectors.toList());
}


}