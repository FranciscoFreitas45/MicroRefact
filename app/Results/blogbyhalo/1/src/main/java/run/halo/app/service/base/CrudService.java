package run.halo.app.service.base;
 import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.exception.NotFoundException;
public interface CrudService {


@NonNull
@Transactional
public List<D> createInBatch(Collection<D> domains)
;

@NonNull
@Transactional
public List<D> updateInBatch(Collection<D> domains)
;

@NonNull
@Transactional
public D removeById(I id)
;

@Transactional
public void removeInBatch(Collection<I> ids)
;

public long count()
;

@NonNull
@Transactional
public D update(D domain)
;

public void mustExistById(I id)
;

@NonNull
public List<D> listAllByIds(Collection<I> ids,Sort sort)
;

@Nullable
@Transactional
public D removeByIdOfNullable(I id)
;

@Transactional
public void remove(D domain)
;

public boolean existsById(I id)
;

@Transactional
public void removeAll()
;

public void flush()
;

@NonNull
public D getById(I id)
;

@Nullable
public D getByIdOfNullable(I id)
;

@NonNull
@Transactional
public D create(D domain)
;

@NonNull
public Optional<D> fetchById(I id)
;

@NonNull
public Page<D> listAll(Pageable pageable)
;

}