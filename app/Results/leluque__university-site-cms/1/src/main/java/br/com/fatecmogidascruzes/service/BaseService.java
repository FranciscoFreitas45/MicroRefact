package br.com.fatecmogidascruzes.service;
 import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.data.SearchCriteria;
public interface BaseService {


public Long countAll()
;

public Page<O> getAll(SearchCriteria searchCriteria)
;

public void removeLogicallyByKey(K key)
;

public Optional<O> getByHash(UUID hash)
;

public Optional<O> getByKey(K key)
;

public void save(Collection<O> objects)
;

public void update(O object)
;

public void removeLogicallyByHash(UUID uuid)
;

public Optional<O> getEnabledByHash(UUID hash)
;

public void removeByKey(K key)
;

public void remove(O object)
;

public Long countEnabled()
;

public Page<O> getEnabled(SearchCriteria searchCriteria)
;

public void removeByHash(UUID uuid)
;

public void removeLogically(O object)
;

}