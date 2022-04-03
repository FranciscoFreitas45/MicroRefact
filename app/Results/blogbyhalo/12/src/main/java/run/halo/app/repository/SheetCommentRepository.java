package run.halo.app.repository;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.SheetComment;
import run.halo.app.model.projection.CommentChildrenCountProjection;
import run.halo.app.model.projection.CommentCountProjection;
import run.halo.app.repository.base.BaseCommentRepository;
public interface SheetCommentRepository extends BaseCommentRepository<SheetComment>{


@Query("select new run.halo.app.model.projection.CommentChildrenCountProjection(count(comment" + ".id), comment.parentId) " + "from SheetComment comment " + "where comment.parentId in ?1 " + "group by comment.parentId")
@NonNull
@Override
public List<CommentChildrenCountProjection> findDirectChildrenCount(Collection<Long> commentIds)
;

@Query("select new run.halo.app.model.projection.CommentCountProjection(count(comment.id), " + "comment.postId) " + "from SheetComment comment " + "where comment.postId in ?1 group by comment.postId")
@NonNull
@Override
public List<CommentCountProjection> countByPostIds(Collection<Integer> sheetIds)
;

}