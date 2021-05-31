public interface UtSubgroupValsEnRepository {

   public List<Object[]> fetchIndicatorAndUnitBySectorNId(Integer SectorNid);
   public List<Object[]> fetchSubgroupByIndicatorAndUnit(Integer IndicatorNid,Integer UnitNId);
}