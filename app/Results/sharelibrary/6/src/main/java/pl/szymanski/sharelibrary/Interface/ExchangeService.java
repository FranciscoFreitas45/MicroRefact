package pl.szymanski.sharelibrary.Interface;
public interface ExchangeService {

   public Exchange getExchangeById(Long id);
   public List<Exchange> getExchangesByUserId(Long userId);
}