package run.halo.app.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.dto.JournalDTO;
import run.halo.app.model.dto.JournalWithCmtCountDTO;
import run.halo.app.model.entity.Journal;
import run.halo.app.model.enums.JournalType;
import run.halo.app.model.params.JournalParam;
import run.halo.app.model.params.JournalQuery;
import run.halo.app.service.base.CrudService;
public interface JournalService extends CrudService<Journal, Integer>{


@NonNull
public Page<Journal> pageBy(JournalType type,Pageable pageable)
;

@NonNull
public Journal createBy(JournalParam journalParam)
;

public Journal updateBy(Journal journal)
;

@NonNull
public JournalDTO convertTo(Journal journal)
;

@NonNull
public Page<JournalWithCmtCountDTO> convertToCmtCountDto(Page<Journal> journalPage)
;

public void increaseLike(long likes,Integer id)
;

public Page<Journal> pageLatest(int top)
;

}