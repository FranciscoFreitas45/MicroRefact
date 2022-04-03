package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ViewEvaluationCtxController {

 private ViewEvaluationCtx viewevaluationctx;


@GetMapping
("/newInstanceFromCurrentContext")
public ViewEvaluationCtx newInstanceFromCurrentContext(){
  return viewevaluationctx.newInstanceFromCurrentContext();
}


}