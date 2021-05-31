public interface IndicatorRepository {

   public List<Object[]> findByIC_Type(String indicatorType);
   public UtIndicatorEn findByIndicator_NId(int indicator_NId);
}