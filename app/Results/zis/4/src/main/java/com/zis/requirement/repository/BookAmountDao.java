package com.zis.requirement.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.dto.RequirementCollectScheduleDTO;
public interface BookAmountDao extends JpaSpecificationExecutor<BookAmount>, PagingAndSortingRepository<BookAmount, Integer>{


@Query("FROM BookAmount WHERE grade<>1 AND college<>'A测试专用' AND bookId = :bookId")
public List<BookAmount> findByBookIdGradeAndCollege(Integer bookId)
;

@Query("FROM BookAmount WHERE grade<>1 AND college<>'A测试专用' AND bookId IN (:bookId)")
public List<BookAmount> findByBookIdListGradeAndCollege(List<Integer> bookId)
;

@Query("from BookAmount where bookId = :bookId and partId = :partId and grade = :grade and term = :term")
public List<BookAmount> findByBookIdPartIdGradeAndTerm(Integer bookId,Integer partId,Integer grade,Integer term)
;

@Query("select distinct college from BookAmount")
public List<String> distinctCollege()
;

public List<BookAmount> findByBookId(Integer bookId)
;

@Query("SELECT new com.zis.requirement.dto.RequirementCollectScheduleDTO(partId, college, institute, partName, grade, term, count(*)) FROM BookAmount GROUP BY partId, college, institute, partName, grade, term")
public List<RequirementCollectScheduleDTO> countGroupByCollegeInseitutePartNamePartIdGradeTerm()
;

@Query("select distinct bookId from BookAmount")
public List<Integer> distinctBookId()
;

@Query("SELECT new com.zis.requirement.dto.RequirementCollectScheduleDTO (partId, college, institute, partName, grade, term, operator, count(*)) FROM BookAmount GROUP BY partId, college, institute, partName, grade, term, operator")
public List<RequirementCollectScheduleDTO> countGroupByCollegeInseitutePartNamePartIdGradeTermOperator()
;

}