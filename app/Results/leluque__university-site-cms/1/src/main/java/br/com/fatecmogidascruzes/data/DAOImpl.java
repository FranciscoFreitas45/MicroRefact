package br.com.fatecmogidascruzes.data;
 import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fatecmogidascruzes.domain.Entity;
public interface DAOImpl {


public Long countByEnabledTrue()
;

public Optional<O> findByHashAndEnabledTrue(String hash)
;

public Page<O> findByEnabledTrue(Pageable pageable)
;

public Optional<O> findByHash(String hash)
;

}