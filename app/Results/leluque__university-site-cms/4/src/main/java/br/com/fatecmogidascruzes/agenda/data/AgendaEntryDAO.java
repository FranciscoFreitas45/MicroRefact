package br.com.fatecmogidascruzes.agenda.data;
 import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fatecmogidascruzes.agenda.AgendaEntry;
import br.com.fatecmogidascruzes.data.DAOImpl;
public interface AgendaEntryDAO extends DAOImpl<AgendaEntry, Long>, JpaRepository<AgendaEntry, Long>{


@Modifying
@Transactional
@Query("UPDATE AgendaEntry ae SET ae.enabled = false WHERE :baseAgendaEntry = ae.baseAgendaEntry AND ae.startDate >= :from")
public void deleteCopiesLogicallyAfter(AgendaEntry baseAgendaEntry,LocalDate from)
;

@Query("SELECT ae FROM AgendaEntry ae WHERE TRUE = ae.enabled AND MONTH(:date) BETWEEN MONTH(ae.startDate) AND MONTH(ae.endDate) AND YEAR(:date) BETWEEN YEAR(ae.startDate) AND YEAR(ae.endDate)")
public List<AgendaEntry> getEnabledByMonth(LocalDate date)
;

@Query("SELECT ae FROM AgendaEntry ae WHERE TRUE = ae.enabled AND (UPPER(ae.name) LIKE CONCAT('%',:filter,'%') OR UPPER(ae.shortDescription) LIKE CONCAT('%',:filter,'%') OR UPPER(ae.longDescription) LIKE CONCAT('%',:filter,'%'))")
public Page<AgendaEntry> getEnabledByFilter(String filter,Pageable pageable)
;

@Query("SELECT ae FROM AgendaEntry ae WHERE TRUE = ae.enabled AND YEAR(:date) BETWEEN YEAR(ae.startDate) AND YEAR(ae.endDate)")
public List<AgendaEntry> getEnabledByYear(LocalDate date)
;

@Query("SELECT ae FROM AgendaEntry ae WHERE TRUE = ae.enabled AND ae.startDate BETWEEN :start AND :end AND ae.endDate BETWEEN :start AND :end")
public List<AgendaEntry> getEnabledAndAllocated(LocalDate start,LocalDate end)
;

@Query("SELECT ae FROM AgendaEntry ae WHERE TRUE = ae.enabled AND :date BETWEEN ae.startDate AND ae.endDate")
public List<AgendaEntry> getEnabledByDay(LocalDate date)
;

@Query("SELECT ae FROM AgendaEntry ae WHERE TRUE = ae.enabled AND ae.startDate IS NULL AND ae.endDate IS NULL")
public List<AgendaEntry> getEnabledAndNotAllocated()
;

}