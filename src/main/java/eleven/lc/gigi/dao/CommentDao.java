package eleven.lc.gigi.dao;

import eleven.lc.gigi.entity.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> selectOrderByTime();

    List<Comment> selectVideoComment(int id);

    List<Comment> selectUserComment(int id);

    int insertNewComment(Comment comment);

    List<Comment> selectResponse(Long id);
}
