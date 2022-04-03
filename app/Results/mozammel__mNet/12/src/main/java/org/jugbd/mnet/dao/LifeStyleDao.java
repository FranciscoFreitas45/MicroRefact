package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.LifeStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LifeStyleDao extends JpaRepository<LifeStyle, Long>{


public void setLifeStyle(Long id,LifeStyle lifeStyle);

public LifeStyle getLifeStyle(Long id);

}