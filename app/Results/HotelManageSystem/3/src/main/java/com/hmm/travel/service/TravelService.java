package com.hmm.travel.service;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import com.hmm.travel.entity.Travel;
import com.hmm.travel.entity.TravelDTO;
import com.hmm.travel.entity.TravelEmpDTO;
public interface TravelService {


public Page<Travel> findTravel(Specification<Travel> spec,Pageable pageable)
;

public List<Map<Object,Object>> findByyearAndOntudytimetravel(Integer year,String userName)
;

public Integer findTatalPersonTravel()
;

public Travel save(Travel entity)
;

public long count(Specification<Travel> spec)
;

public void deleteAll(Long[] ids)
;

public Page<TravelDTO> findTodoTasks(String userId,Pageable pageable)
;

public Page<TravelEmpDTO> findAll(String userId,String groupName,Pageable pageable)
;

public void startWorkflow(String userId,Long travelId,Map<String,Object> variables)
;

public Page<TravelEmpDTO> findAllQueryDTO(Specification<Travel> whereClause,Pageable pageable)
;

public boolean existsById(Long id)
;

public Optional<Travel> findById(Long id)
;

public void deleteById(Long id)
;

public Page<TravelEmpDTO> findByUserId(Specification<Travel> whereClause,String userId,Pageable pageable)
;

public List<Map<Object,Object>> findtravel(Integer year)
;

public void claim(String taskId,String userId)
;

public void complete(String taskId,Map<String,Object> variables)
;

public float findTotalTravelAllowance(String userName)
;

}