package eleven.lc.gigi.service;

import eleven.lc.gigi.entity.Comment;

import java.util.List;

public interface CommentService {
    Boolean addComment(Comment comment);

    List<Comment> listResponse(Long id);
}
