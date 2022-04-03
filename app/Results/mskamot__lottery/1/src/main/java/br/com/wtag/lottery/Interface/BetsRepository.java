package br.com.wtag.lottery.Interface;
public interface BetsRepository {

   public Object save(Object Object);
   public List<Bets> findByEmailOrderByRegisteredAsc(String email);
}