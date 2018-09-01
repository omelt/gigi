package eleven.lc.gigi.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleven.lc.gigi.dao.VideoDao;
import eleven.lc.gigi.entity.Comment;
import eleven.lc.gigi.entity.Video;
import eleven.lc.gigi.myutil.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/search")
public class SearchAction {

    @Autowired
    VideoDao videoDao;

    @Value("${eleven.lc.gigi.pageSize}")
    int pageSize;

    @RequestMapping(value = {"/{data}","/{data}/{nowPage}"})
    public String Search(Model model, @PathVariable String data, @PathVariable(required = false) Integer nowPage){

        if(nowPage==null || nowPage<1) nowPage=1;

        Set<Video> res=new HashSet<>();

        Page page = PageHelper.startPage(nowPage, pageSize, true);

        List<Video> list=videoDao.selectLikeTitle(data);

        PageBean<Video> pageBean=new PageBean(nowPage,pageSize,page.getPages());

        pageBean.setItems(list);

        if(list==null || list.size()==0) model.addAttribute("dataFlag",false);
        else{
            model.addAttribute("dataFlag",true);
        }

        model.addAttribute("pageBean",pageBean);

        model.addAttribute("tarUrl","/search/"+data);

        return "block";
    }
    @RequestMapping(value = {"/tag/{data}","/tag/{data}/{nowPage}"})
    public String SearchTag(Model model,@PathVariable String data, @PathVariable(required = false) Integer nowPage){

        if(nowPage==null || nowPage<1) nowPage=1;

        Set<Video> res=new HashSet<>();

        Page page = PageHelper.startPage(nowPage, pageSize, true);

        List<Video> list=videoDao.selectLikeTag(data);

        PageBean<Video> pageBean=new PageBean(nowPage,pageSize,page.getPages());

        pageBean.setItems(list);

        if(list==null || list.size()==0) model.addAttribute("dataFlag",false);
        else{
            model.addAttribute("dataFlag",true);
        }

        model.addAttribute("pageBean",pageBean);

        model.addAttribute("tarUrl","/search/tag/"+data);

        return "block";
    }
}
