public interface UtDataRepository {

   public void save(Iterable<UtData> utDatas);
   public List<UtData> getData(int subSector,int timePeriod,int indicator,int unit,int subgroup);
   public List<Object[]> getByTimePeriod(int timeperiodId);
   public List<Object[]> findDataByTimePeriodAndArea(Integer timeperiodId,List<Integer> areaList,Integer[] iusIds);
   public List<Object[]> getABRCCData(List<String> areaNames);
}