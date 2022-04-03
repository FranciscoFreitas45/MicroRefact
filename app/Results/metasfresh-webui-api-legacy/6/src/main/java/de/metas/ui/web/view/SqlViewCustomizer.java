package de.metas.ui.web.view;
 import de.metas.ui.web.view.descriptor.SqlViewBinding;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.WindowId;
public interface SqlViewCustomizer extends ViewRowCustomizer{


@Override
public void customizeViewRow(ViewRow.Builder rowBuilder){
// nothing
}
;

public void customizeViewLayout(ViewLayout.ChangeBuilder viewLayoutBuilder){
// nothing
}
;

public ViewProfile getProfile()
;

public WindowId getWindowId()
;

public void customizeSqlViewBinding(SqlViewBinding.Builder sqlViewBindingBuilder){
// nothing
}
;

}