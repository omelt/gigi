package eleven.lc.gigi.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleven.lc.gigi.dao.VideoDao;
import eleven.lc.gigi.entity.Comment;
import eleven.lc.gigi.entity.Video;
import eleven.lc.gigi.myutil.PageBean;
import eleven.lc.gigi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentAction {

    @Autowired
    VideoDao videoDao;

    @Autowired
    CommentService commentService;

    @Value("${eleven.lc.gigi.pageSize}")
    int pageSize;

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Object getCommentPage(@RequestParam Long id, @RequestParam Integer nowPage){
        if(nowPage==null || nowPage<1)
            nowPage=1;

        Video video = videoDao.selectById(id);

        Page page = PageHelper.startPage(nowPage, pageSize, true);

        List<Comment> list=video.getComments();

        PageBean<Comment> pageBean=new PageBean(nowPage,pageSize,page.getPages());

        pageBean.setItems(list);

        return JSON.toJSON(pageBean);
    }

    @RequestMapping(value = "/response",method = RequestMethod.POST)
    public Object getResponse(@RequestParam Long id){
        return JSON.toJSON(commentService.listResponse(id));
    };
}
