package eleven.lc.gigi.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleven.lc.gigi.entity.User;
import eleven.lc.gigi.entity.UserCheck;
import eleven.lc.gigi.entity.Video;
import eleven.lc.gigi.myutil.DrawImage;
import eleven.lc.gigi.myutil.PageBean;
import eleven.lc.gigi.myutil.RSAUtil;
import eleven.lc.gigi.service.Impl.SchedulService;
import eleven.lc.gigi.service.UserCheckService;
import eleven.lc.gigi.service.UserService;
import eleven.lc.gigi.service.VideoService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;
import java.util.*;

//User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//当前用户

@Controller
public class PageAction {

    @Autowired
    VideoService videoService;

    @Autowired
    UserCheckService userCheckService;

    @Autowired
    UserService userService;

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("videoAllList", SchedulService.getAllList());
        model.addAttribute("videoFeaturedList", SchedulService.getFeaturedList());
        model.addAttribute("videoHeadList", SchedulService.getHeadList());
        model.addAttribute("videoHotList", SchedulService.getHotList());

        model.addAttribute("videoDateList", videoService.listByDate());
        model.addAttribute("videoRandomList", videoService.listByRandom());

    }

    @RequestMapping("/validate")
    public String emailValidate(@RequestParam String email,@RequestParam String uid,Model model){
        UserCheck userCheck=userCheckService.getById(uid);
        if(userCheck==null){
            model.addAttribute("error","不存在的注册信息或已经过期");
        }else{
            Date date = new Date(System.currentTimeMillis());
            if(date.compareTo(userCheck.getDate())>-1){
                model.addAttribute("error","信息已经过期");
            }else {
                userService.addUser(userCheck);
            }
        }

        return "validate";
    }

    @RequestMapping(value = {"/register"},method = RequestMethod.GET)
    public String register(){return "register";}

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String Contact() {
        return "contact";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String Home() {
        return "home";
    }

    @RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public String Play(@PathVariable long id,Model model) {

        Video video=videoService.getById(id);

        videoService.countTimes(id);


        if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
            User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getCollects().contains(video)) model.addAttribute("isCollect",true);
        }


        model.addAttribute("curVideo",video);
        return "play";
    }

    @RequestMapping(value = {"/login","/user/login"}, method = RequestMethod.GET)
    public String Login() {
        return "login";
    }

    @Value("${eleven.lc.gigi.pageSize}")
    int pageSize;

    @RequestMapping(value = {"/follows"}, method = RequestMethod.GET)
    public String follows() {
        return "follows";
    }
    @RequestMapping(value = {"/followed"}, method = RequestMethod.GET)
    public String followed() {
        return "followed";
    }
    @RequestMapping(value = {"/myCollect"}, method = RequestMethod.GET)
    public String myCollect() {
        return "myCollect";
    }
    @RequestMapping(value = {"/myVideo"}, method = RequestMethod.GET)
    public String myVideo() {
        return "myVideo";
    }

}
