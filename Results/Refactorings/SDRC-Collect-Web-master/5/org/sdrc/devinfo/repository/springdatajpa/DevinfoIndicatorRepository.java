import java.util.List;
import org.sdrc.devinfo.domain.UtIndicatorEn;
import org.sdrc.devinfo.repository.IndicatorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface DevinfoIndicatorRepository extends JpaRepository<UtIndicatorEn, Long> {


@Override
@Query("SELECT uticius.IUSNId FROM UtIcIus uticius WHERE uticius.IC_NId=:sectorNid")
public List<Object[]> getIUSNIdList(Integer sectorNid)


@Override
@Query(value = "SELECT " + "I.Indicator_NId," + "cast(I.Indicator_Info as varchar(500)) as Indicator_Info," + "IU.IC_NId," + "IU.IUSNId," + "(cast(I.Indicator_Name as varchar(250))+','+cast(SG.Subgroup_Val as varchar) +' ('+cast(U.Unit_Name as varchar)+')') as 'IUSName' from " + "ut_unit_en U," + "ut_indicator_en I," + "ut_subgroup_vals_en SG, " + "ut_ic_ius IU," + "ut_indicator_unit_subgroup IUS " + "WHERE IUS.IUSNId=IU.IUSNId AND " + "IUS.Indicator_NId = I.Indicator_NId AND " + "IUS.Unit_NId = U.Unit_NId AND " + "IUS.Subgroup_Val_NId = SG.Subgroup_Val_NId AND I.Short_Name =:indicatorType", nativeQuery = true)
public List<Object[]> findByIC_Type(String indicatorType)


@Override
@Query("SELECT  utIndicatorEn FROM UtData utData, UtIndicatorEn utIndicatorEn" + " WHERE utData.indicator_NId = utIndicatorEn.indicator_NId AND " + " utData.indicator_NId = :indicator_NId ")
public UtIndicatorEn findUtDataByIndicator_NId(int indicator_NId)


@Override
@Query("SELECT distinct utData.IUSNId" + " FROM UtData utData " + " WHERE utData.IUSNId IN (:iusNid) AND utData.area_NId IN (:areaNid)")
public List<Object[]> getFilteredIUSFromUTDataList(Integer[] iusNid,Integer[] areaNid)


@Override
@Query("SELECT  utIndicatorEn FROM UtData utData, UtIndicatorEn utIndicatorEn" + " WHERE utData.indicator_NId = utIndicatorEn.indicator_NId AND " + " utData.IUSNId = :iusNID ")
public UtIndicatorEn findByIndicator_NId(int indicator_NId)


}