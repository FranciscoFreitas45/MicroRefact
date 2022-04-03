package br.com.fatecmogidascruzes.domain;
 import java.time.LocalDateTime;
import java.util.UUID;
public interface Entity {


public LocalDateTime getCreationDate()
;

public void setEnabled(boolean enabled)
;

public void setCreationDate(LocalDateTime creationDate)
;

public UUID getHash()
;

public boolean getEnabled()
;

public boolean isEnabled()
;

public void setId(Long id)
;

public Long getId()
;

public void setHash(UUID hash)
;

public LocalDateTime getLastUpdate()
;

public void setLastUpdate(LocalDateTime lastUpdate)
;

}