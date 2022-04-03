package pl.szymanski.sharelibrary.services.ports;
 import pl.szymanski.sharelibrary.entity.Exchange;
import pl.szymanski.sharelibrary.entity.Requirement;
import pl.szymanski.sharelibrary.requests.AddExchangeRequest;
import pl.szymanski.sharelibrary.requests.ExecuteExchangeRequest;
import pl.szymanski.sharelibrary.response.ExchangeResponse;
import java.util.List;
public interface ExchangeService {


public List<ExchangeResponse> filter(Double latitude,Double longitude,Double radius,List<String> categories,String query,Integer languageId,List<Integer> conditions)
;

public Exchange saveExchange(AddExchangeRequest addExchangeRequest)
;

public List<Exchange> getExchangesByCoordinatesAndRadius(double latitude,double longitude,double radius)
;

public List<Requirement> getRequirements(Long exchangeId)
;

public Exchange executeExchange(ExecuteExchangeRequest executeExchangeRequest)
;

public List<Exchange> getExchangesByUserId(Long userId)
;

public List<Exchange> getExchangesLinkedByUser(Long userId)
;

public Exchange getExchangeById(Long id)
;

public List<Exchange> getExchangesByWithUserId(Long userId)
;

public double countDistanceBetweenPoints(double lat1,double lon1,double lat2,double lon2)
;

public void finishExchange(Long exchangeId)
;

}