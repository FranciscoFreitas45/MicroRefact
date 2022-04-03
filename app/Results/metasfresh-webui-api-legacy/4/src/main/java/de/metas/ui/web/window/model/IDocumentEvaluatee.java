package de.metas.ui.web.window.model;
 import org.compiere.util.Evaluatee;
public interface IDocumentEvaluatee extends Evaluatee{


public IDocumentEvaluatee excludingFields(String fieldNamesToExclude)
;

public IDocumentEvaluatee fieldInScope(String fieldNameInScope)
;

}