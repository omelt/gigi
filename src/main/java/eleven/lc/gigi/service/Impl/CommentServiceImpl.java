package eleven.lc.gigi.service.Impl;

import eleven.lc.gigi.dao.CommentDao;
import eleven.lc.gigi.dao.VideoDao;
import eleven.lc.gigi.entity.Comment;
import eleven.lc.gigi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
    CommentDao commentDao;

    @Autowired
    VideoDao videoDao;

    @Override
    @Transactional
    public Boolean addComment(Comment comment) {
        if (commentDao.insertNewComment(comment)>0)
            if(videoDao.updatePlusCommentNum(comment.getVideoId())>0)
                return true;
        return false;
    }

    @Override
    public List<Comment> listResponse(Long id) {
        return commentDao.selectResponse(id);
    }
}
