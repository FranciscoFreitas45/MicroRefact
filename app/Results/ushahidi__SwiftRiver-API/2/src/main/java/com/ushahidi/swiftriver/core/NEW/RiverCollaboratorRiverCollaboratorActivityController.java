package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.RiverCollaborator;
@RestController
@CrossOrigin
public class RiverCollaboratorRiverCollaboratorActivityController {

@Autowired
 private RiverCollaboratorRiverCollaboratorActivityService rivercollaboratorrivercollaboratoractivityservice;


@GetMapping
("/RiverCollaboratorActivity/{id}/RiverCollaborator/getActionOnObj")
public RiverCollaborator getActionOnObj(@PathVariable(name="id") Long idD9W3){
return rivercollaboratorrivercollaboratoractivityservice.getActionOnObj(idD9W3);
}


@PutMapping
("/RiverCollaboratorActivity/{id}/RiverCollaborator/setActionOnObj")
public void setActionOnObj(@PathVariable(name="id") Long idD9W3,@RequestBody RiverCollaborator actionOnObj){
rivercollaboratorrivercollaboratoractivityservice.setActionOnObj(idD9W3,actionOnObj);
}


}