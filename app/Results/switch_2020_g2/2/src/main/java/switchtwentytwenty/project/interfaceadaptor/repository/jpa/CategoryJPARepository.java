package switchtwentytwenty.project.interfaceadaptor.repository.jpa;
 import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.CategoryJPA;
public interface CategoryJPARepository extends CrudRepository<CategoryJPA, String>{


@Query("FROM CategoryJPA WHERE parentID IS NULL and designation = ?1")
public Iterable<CategoryJPA> findCategoryJPAByDesignation(String designation)
;

@Query("FROM CategoryJPA WHERE parentID = ?1 and isStandard = true")
public Iterable<CategoryJPA> findCategoryJPAStandardByParentID(String parentID)
;

@Query("FROM CategoryJPA WHERE familyId = ?1 or isStandard = true")
public Iterable<CategoryJPA> findByFamilyID(String familyID)
;

@Query("FROM CategoryJPA WHERE parentID = ?1")
public Iterable<CategoryJPA> findCategoryJPAByParentIDWithDesignation(String parentID,String designation)
;

@Query("FROM CategoryJPA WHERE isStandard = true")
public Iterable<CategoryJPA> findCategoryJPAStandard()
;

@Query("FROM CategoryJPA WHERE parentID = ?1")
public Iterable<CategoryJPA> findCategoryJPAByParentID(String parentID)
;

}