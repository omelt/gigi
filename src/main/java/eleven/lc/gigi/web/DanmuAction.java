package eleven.lc.gigi.web;


import com.alibaba.fastjson.JSON;
import eleven.lc.gigi.entity.Danmu;
import eleven.lc.gigi.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/danmu")
public class DanmuAction {

    @Autowired
    DanmuService danmuService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String pushDanmu(@PathVariable Long id){
        return danmuService.listDanmuFromVideo(id).toString();
    }

    @RequestMapping(value = "/upload/{id}",method = RequestMethod.POST)
    @ResponseBody
    public String publishDanmu(@PathVariable Long id,@RequestParam String danmu) {

        Danmu objDanmu= JSON.parseObject(danmu, Danmu.class);

        objDanmu.setVideoId(id);

        danmuService.addDanmuIntoVideo(objDanmu);

        return "ok";
    }

}
