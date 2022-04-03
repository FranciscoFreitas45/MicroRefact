package com.sprint2.service;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sprint2.model.Land;
public interface ILandService {


public Land getLandBylandId(Integer landId)
;

public boolean deleteLandbylandId(Integer landId)
;

public Land addLand(Land Land)
;

public Land updateLand(Land Land)
;

public List<Land> getAllLand()
;

}