import java.util.List;
import org.sdrc.devinfo.domain.UtAreaEn;
import org.sdrc.devinfo.repository.UtAreaEnRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
public interface DevinfoUtAreaEnRepository extends UtAreaEnRepository {


@Override
@Query("SELECT utArea.area_NId From UtAreaEn utArea where utArea.area_Name=:area_name and utArea.area_Parent_NId=:parent_nid")
public int findAreaNidByParent(String area_name,int parent_nid)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_NId=(SELECT utArea.area_Parent_NId From UtAreaEn utArea where utArea.area_ID=:area_ID)")
public UtAreaEn findParentAreaByAreaId(String areaId)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_Parent_NId in (SELECT area.area_NId From UtAreaEn area where area.area_Parent_NId = (SELECT a.area_NId From UtAreaEn a where a.area_ID=:areaCode))")
public List<UtAreaEn> getClustersByDistrictAreaId(String areaCode)


@Override
@Query("SELECT utArea.area_Name From UtAreaEn utArea where utArea.area_ID=:areaCode")
public String areaNamebyAreaID(String areaCode)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_Level=:area_Level")
public List<UtAreaEn> findByAreaLevel(int area_Level)


@Override
@Query("SELECT utArea.area_NId From UtAreaEn utArea where utArea.area_Name=:area_name")
public int findAreaNid(String area_name)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_ID=:area_ID")
public UtAreaEn findByAreaID(String area_ID)


@Override
@Query("SELECT utArea.area_ID From UtAreaEn utArea where utArea.area_Name=:area_name")
public String findAreaIdbyAreaName(String area_name)


@Override
@Query("SELECT utArea.area_NId From UtAreaEn utArea where utArea.area_Parent_NId = (SELECT area.area_NId From UtAreaEn area where area.area_ID = :selectedGranularity)")
public List<Integer> findAreaNidbyGranularity(String selectedGranularity)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_Parent_NId = (SELECT a.area_NId From UtAreaEn a where a.area_ID=:areaCode)")
public List<UtAreaEn> findByAreaParentId(String areaCode)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_NId=:areaNId")
public UtAreaEn findbyAreaNId(int areaNId)


@Override
@Query("SELECT utArea From UtAreaEn utArea where utArea.area_Parent_NId=:area_Parent_NId")
public List<UtAreaEn> findByAreaParentNId(int area_Parent_NId)


}