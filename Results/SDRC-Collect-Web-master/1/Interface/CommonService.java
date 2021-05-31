public interface CommonService {

   public Double getMinValue(List<Object[]> data);
   public Double getMaxValue(List<Object[]> data);
   public List<ValueObject> populateLegends(List<Object[]> data,String indicatorId);
   public void setCssForDataModel(List<ValueObject> list,UtDataModel data,Double value,String indicatorId);
}