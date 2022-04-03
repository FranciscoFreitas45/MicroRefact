package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.enums.ExchangeStatus;
import java.util.List;
import java.util.Optional;
public interface ExchangeRepository {


public Exchange saveExchange(Exchange exchange)
;

public List<Exchange> getExchangeByCoordinatesAndRadius(Double latitude,Double longitude,Double radius)
;

public List<Exchange> getAll()
;

public List<Exchange> getExchangeByStatus(ExchangeStatus exchangeStatus)
;

public List<Exchange> getExchangeByBoundingCoordinates(double latMin,double latMax,double longMin,double longMax)
;

public Optional<Exchange> getExchangeById(Long id)
;

public List<Exchange> getExchangesLinkedWithUser(Long userId)
;

}