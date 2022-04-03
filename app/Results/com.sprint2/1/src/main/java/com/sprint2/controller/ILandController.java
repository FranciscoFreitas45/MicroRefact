package com.sprint2.controller;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Land;
public interface ILandController {


@ResponseBody
public Land getLandBylandId(Integer landId)
;

@ResponseBody
public boolean deleteLandbylandId(Integer landId)
;

@ResponseBody
public Land addLand(Land Land)
;

@ResponseBody
public Land updateLand(Land Land)
;

@ResponseBody
public List<Land> getAllLand()
;

}