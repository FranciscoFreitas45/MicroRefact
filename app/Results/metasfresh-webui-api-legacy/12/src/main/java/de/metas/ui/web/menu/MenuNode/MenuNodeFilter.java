package de.metas.ui.web.menu.MenuNode;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.adempiere.util.lang.IPair;
import org.adempiere.util.lang.ImmutablePair;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import de.metas.ui.web.menu.MenuNode.MenuNodeFilter.MenuNodeFilterResolution;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.util.Check;
@FunctionalInterface
public interface MenuNodeFilter {


public MenuNodeFilterResolution check(MenuNode node)
;

}