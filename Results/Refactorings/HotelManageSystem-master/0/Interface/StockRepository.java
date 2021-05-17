public interface StockRepository {

   public List<Stock> findByStockType(StockType stockType);
}