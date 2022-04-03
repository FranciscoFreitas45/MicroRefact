package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.LifeStyle;
import org.springframework.stereotype.Component;
@Component
public interface LifeStyleService {


public LifeStyle save(LifeStyle lifeStyle)
;

public LifeStyle findOne(Long id)
;

}