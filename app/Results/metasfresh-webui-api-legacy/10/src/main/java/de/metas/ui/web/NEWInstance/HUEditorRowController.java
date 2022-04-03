package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUEditorRowController {

 private HUEditorRow hueditorrow;


@GetMapping
("/isHUPlanningReceiptOwnerPM")
public boolean isHUPlanningReceiptOwnerPM(){
  return hueditorrow.isHUPlanningReceiptOwnerPM();
}


@GetMapping
("/getM_HU")
public I_M_HU getM_HU(){
  return hueditorrow.getM_HU();
}


@GetMapping
("/getC_UOM")
public I_C_UOM getC_UOM(){
  return hueditorrow.getC_UOM();
}


@GetMapping
("/isCU")
public boolean isCU(){
  return hueditorrow.isCU();
}


@GetMapping
("/isTU")
public boolean isTU(){
  return hueditorrow.isTU();
}


@GetMapping
("/isLU")
public boolean isLU(){
  return hueditorrow.isLU();
}


@GetMapping
("/getProductId")
public ProductId getProductId(){
  return hueditorrow.getProductId();
}


@GetMapping
("/setRowId")
public Builder setRowId(@RequestParam(name = "rowId") HUEditorRowId rowId){
  return hueditorrow.setRowId(rowId);
}


@GetMapping
("/isTopLevel")
public boolean isTopLevel(){
  return hueditorrow.isTopLevel();
}


@GetMapping
("/cast")
public HUEditorRow cast(@RequestParam(name = "viewRow") IViewRow viewRow){
  return hueditorrow.cast(viewRow);
}


@GetMapping
("/isHUStatusActive")
public boolean isHUStatusActive(){
  return hueditorrow.isHUStatusActive();
}


}